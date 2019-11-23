package me.shouheng.notepal.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.Collections;
import java.util.Objects;

import me.shouheng.commons.event.RxMessage;
import me.shouheng.commons.utils.ColorUtils;
import me.shouheng.commons.widget.recycler.CustomItemAnimator;
import me.shouheng.commons.widget.recycler.DividerItemDecoration;
import me.shouheng.data.entity.Category;
import me.shouheng.data.model.enums.Status;
import me.shouheng.mvvm.base.anno.FragmentConfiguration;
import me.shouheng.notepal.R;
import me.shouheng.notepal.adapter.CategoriesAdapter;
import me.shouheng.notepal.databinding.FragmentCategoriesBinding;
import me.shouheng.notepal.dialog.CategoryEditDialog;
import me.shouheng.notepal.vm.CategoriesViewModel;
import me.shouheng.utils.ui.ToastUtils;
import me.shouheng.utils.ui.ViewUtils;

/**
 * Fragment used to display the categories.
 *
 * Created by WngShhng (shouheng2015@gmail.com) on 2017/3/29.
 */
@FragmentConfiguration(layoutResId = R.layout.fragment_categories)
public class CategoriesFragment extends BaseFragment<FragmentCategoriesBinding, CategoriesViewModel > implements BaseQuickAdapter.OnItemClickListener {

    /**
     * The argument key for this fragment. The status of current categories list.
     * Or null of showing the normal categories.
     */
    public static final String ARGS_KEY_STATUS = "__args_key_status";

    private RecyclerView.OnScrollListener scrollListener;
    private CategoriesAdapter mAdapter;

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            if (getArguments() != null && getArguments().containsKey(ARGS_KEY_STATUS)) {
                Status status = (Status) getArguments().get(ARGS_KEY_STATUS);
                getVM().setStatus(status);
            }
        }

        configToolbar();

        /* Config the categories list. */
        mAdapter = new CategoriesAdapter(getContext(), Collections.emptyList());
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.iv_more) {
                Category category = mAdapter.getItem(position);
                if (category != null) {
                    popMenu(view, category);
                }
            }
        });
        mAdapter.setOnItemClickListener(this);
        getBinding().rvCategories.setEmptyView(getBinding().ev);
        getBinding().rvCategories.setHasFixedSize(true);
        getBinding().rvCategories.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),
                DividerItemDecoration.VERTICAL_LIST, isDarkTheme()));
        getBinding().rvCategories.setItemAnimator(new CustomItemAnimator());
        getBinding().rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        ((TextView) getBinding().ev.findViewById(R.id.tv_empty_detail)).setText(getVM().getEmptySubTitle());
        getBinding().rvCategories.setAdapter(mAdapter);
        if (scrollListener != null) {
            getBinding().rvCategories.addOnScrollListener(scrollListener);
        }

        addSubscriptions();

        getVM().fetchCategories();
    }

    private void configToolbar() {
        Activity activity = getActivity();
        if (activity != null) {
            ActionBar ab = ((AppCompatActivity) activity).getSupportActionBar();
            if (ab != null) {
                ab.setTitle(R.string.drawer_menu_categories);
                ab.setSubtitle(null);
                ab.setDisplayHomeAsUpEnabled(true);
                ab.setHomeAsUpIndicator(ColorUtils.tintDrawable(R.drawable.ic_menu_black,
                        getThemeStyle().isDarkTheme ? Color.WHITE : Color.BLACK));
            }
        }
    }

    private void addSubscriptions() {
        getVM().getListObservable(Category.class).observe(this, resources -> {
            assert resources != null;
            switch (resources.status) {
                case SUCCESS:
                    mAdapter.setNewData(resources.data);
                    getBinding().ev.showEmpty();
                    break;
                case FAILED:
                    ToastUtils.showShort(R.string.text_failed);
                    getBinding().ev.showEmpty();
                    break;
                case LOADING:
                    getBinding().ev.showLoading();
                    break;
            }
        });
        getVM().getObservable(Category.class).observe(this, resource -> {
            assert resource != null;
            switch (resource.status) {
                case SUCCESS:
                    getVM().fetchCategories();
                    break;
                case LOADING:
                    break;
                case FAILED:
                    ToastUtils.showShort(R.string.text_failed);
                    break;
            }
        });
        addSubscription(RxMessage.class, RxMessage.CODE_CATEGORY_DATA_CHANGED,
                rxMessage -> getVM().fetchCategories());
        addSubscription(RxMessage.class, RxMessage.CODE_NOTE_DATA_CHANGED,
                rxMessage -> getVM().fetchCategories());
    }

    public void addCategory(Category category) {
        mAdapter.addData(0, category);
        getBinding().rvCategories.smoothScrollToPosition(0);
    }

    public void setScrollListener(RecyclerView.OnScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    private void popMenu(View v, Category param) {
        PopupMenu popupM = new PopupMenu(Objects.requireNonNull(getContext()), v);
        popupM.inflate(R.menu.category_pop_menu);
        popupM.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.action_edit:
                    CategoryEditDialog.newInstance(param,
                            category -> getVM().updateCategory(category)
                    ).show(getChildFragmentManager(), "CATEGORY_EDIT_DIALOG");
                    break;
                case R.id.action_delete:
                    getVM().updateCategory(param, Status.DELETED);
                    break;
                default:
                    // noop
            }
            return true;
        });
        popupM.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_capture) {
            createScreenCapture(getBinding().rvCategories, ViewUtils.dp2px(60));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Activity activity = getActivity();
        if (activity instanceof CategoriesInteraction) {
            ((CategoriesInteraction) activity).onCategorySelected(mAdapter.getItem(position));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if (activity instanceof CategoriesInteraction) {
            ((CategoriesInteraction) activity).onResumeToCategory();
        }
        configToolbar();
    }

    public interface CategoriesInteraction {

        default void onResumeToCategory() {}

        default void onCategorySelected(Category category) {}
    }
}