package com.example.smile.cnsjzhushou.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.SearchResult;
import com.example.smile.cnsjzhushou.common.rx.RxSchedulers;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerSearchComponent;
import com.example.smile.cnsjzhushou.di.module.SearchModule;
import com.example.smile.cnsjzhushou.presenter.SearchPresenter;
import com.example.smile.cnsjzhushou.presenter.contract.SearchContract;
import com.example.smile.cnsjzhushou.ui.adapter.AppInfoAdapter;
import com.example.smile.cnsjzhushou.ui.adapter.SearchHistoryAdatper;
import com.example.smile.cnsjzhushou.ui.adapter.SuggestionAdapter;
import com.example.smile.cnsjzhushou.ui.decoration.DividerItemDecoration;
import com.example.smile.cnsjzhushou.ui.widget.SpaceItemDecoration2;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by LiBing
 * on 2017/7/22 0022
 * describe:
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.SearchView {
    @BindView(R.id.searchTextView)
    EditText mSearchTextView;
    @BindView(R.id.action_clear_btn)
    ImageView mActionClearBtn;
    @BindView(R.id.search_bar)
    RelativeLayout mSearchBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_clear)
    ImageView mBtnClear;
    @BindView(R.id.recycler_view_history)
    RecyclerView mRecyclerViewHistory;
    @BindView(R.id.layout_history)
    LinearLayout mLayoutHistory;
    @BindView(R.id.recycler_view_suggestion)
    RecyclerView mRecyclerViewSuggestion;
    @BindView(R.id.recycler_view_result)
    RecyclerView mRecyclerViewResult;
    @BindView(R.id.activity_search)
    LinearLayout mActivitySearch;

    private SuggestionAdapter mSuggestionAdapter;
    private AppInfoAdapter mAppInfoAdapter;
    private SearchHistoryAdatper mHistoryAdatper;

    @Inject
    RxDownload rxDownload;

    private Disposable disposable;

    public static final int STATUS_REQUESTING = 0;
    public static final int STATUS_FINISH = 1;
    public int status = STATUS_FINISH;

    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSearchComponent.builder().appComponent(appComponent)
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void init() {
        mPresenter.showHistory();
        initView();
        setupSearchView();
        setupSuggestionRecyclerView();
        initSearchResultRecycleView();
    }

    private void initView() {

        mToolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mActionClearBtn.setImageDrawable(new IconicsDrawable(this, Ionicons.Icon.ion_ios_close_empty)
                .color(ContextCompat.getColor(this, R.color.white)).sizeDp(16));

        mBtnClear.setImageDrawable(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline)
                .color(ContextCompat.getColor(this, R.color.md_grey_600)).sizeDp(16));

        RxView.clicks(mBtnClear).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {

            }
        });
    }

    private void setupSearchView() {

        RxView.clicks(mActionClearBtn).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                mSearchTextView.setText("");
                mLayoutHistory.setVisibility(View.VISIBLE);
                mRecyclerViewSuggestion.setVisibility(View.GONE);
                mRecyclerViewResult.setVisibility(View.GONE);
            }
        });

        //软键盘上的搜索按钮
        RxTextView.editorActions(mSearchTextView).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                search(mSearchTextView.getText().toString().trim());
            }
        });

        disposable = RxTextView.textChanges(mSearchTextView)
                .debounce(400, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.<CharSequence>io_main())
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        return charSequence.toString().trim().length() > 0;
                    }
                })
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {

                        if (charSequence.length() > 0) {
                            mActionClearBtn.setVisibility(View.VISIBLE);
                        } else {
                            mActionClearBtn.setVisibility(View.GONE);
                        }

                        mPresenter.getSuggestions(charSequence.toString().trim());
                    }
                });
    }

    private void setupSuggestionRecyclerView() {

        mSuggestionAdapter = new SuggestionAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewSuggestion.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerViewSuggestion.addItemDecoration(itemDecoration);

        mRecyclerViewSuggestion.setAdapter(mSuggestionAdapter);

        mRecyclerViewSuggestion.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                search(mSuggestionAdapter.getItem(position));
            }
        });

    }

    private void initSearchResultRecycleView() {
        mAppInfoAdapter = AppInfoAdapter.builder().showBrief(false).showCategoryName(true).rxDownload(rxDownload).build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewResult.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerViewResult.addItemDecoration(itemDecoration);

        mRecyclerViewResult.setAdapter(mAppInfoAdapter);

        mRecyclerViewResult.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private void initHistoryRecycleView(List<String> list) {
        mHistoryAdatper = new SearchHistoryAdatper();
        mHistoryAdatper.addData(list);
        RecyclerView.LayoutManager lm = new GridLayoutManager(this, 5);
        SpaceItemDecoration2 itemd = new SpaceItemDecoration2(10);
        mRecyclerViewHistory.addItemDecoration(itemd);

        mRecyclerViewHistory.setLayoutManager(lm);
        mRecyclerViewHistory.setAdapter(mHistoryAdatper);

        mRecyclerViewHistory.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                search(mHistoryAdatper.getItem(position));
            }
        });
    }

    private void search(String keyword) {
        mPresenter.search(keyword);
    }

    @Override
    public void showSearchHistory(List<String> list) {
        initHistoryRecycleView(list);
        mRecyclerViewSuggestion.setVisibility(View.GONE);
        mLayoutHistory.setVisibility(View.VISIBLE);
        mRecyclerViewResult.setVisibility(View.GONE);
    }

    @Override
    public void showSuggestions(List<String> list) {
        mSuggestionAdapter.setNewData(list);
        mRecyclerViewSuggestion.setVisibility(View.VISIBLE);
        mLayoutHistory.setVisibility(View.GONE);
        mRecyclerViewResult.setVisibility(View.GONE);
    }

    @Override
    public void showSearchResult(SearchResult result) {
        mAppInfoAdapter.setNewData(result.getListApp());
        mRecyclerViewSuggestion.setVisibility(View.GONE);
        mLayoutHistory.setVisibility(View.GONE);
        mRecyclerViewResult.setVisibility(View.VISIBLE);
    }
}
