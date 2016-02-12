package com.example.ec_dev.searchviewapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "param";

    // TODO: Rename and change types of parameters
    private String mParam;

    private OnFragmentInteractionListener mListener;

    private SearchView searchView;
    private ListView listView;
    private String[] listArr = {"Kato", "Sato", "Saito", "Kaito", "Naito"};

    public PageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param Parameter 1.
     * @return A new instance of fragment PageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageFragment newInstance(String param) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_page, container, false);
        this.searchView = (SearchView)view.findViewById(R.id.searchView);
        // アイコン＋検索ボックスを表示
        this.searchView.setIconifiedByDefault(false);
        this.listView = (ListView)view.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, listArr);
        this.listView.setAdapter(adapter);
        this.listView.setTextFilterEnabled(false);

        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // SubmitボタンまたはEnterKeyが押されたときに呼び出される処理
                SearchView searchView = (SearchView)view.findViewById(R.id.searchView);
                ListView listView = (ListView)view.findViewById(R.id.listView);
                android.widget.Filter filter = ((Filterable)listView.getAdapter()).getFilter();
                if(TextUtils.isEmpty(s)) {
                    // リストの検索フィルターをクリア
                    listView.clearTextFilter();
                } else {
                    // リストをフィルタリング
                    filter.filter(s);
                    // 検索ボックスからフォーカスを外す
                    searchView.clearFocus();
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // 検索ボックスに入力される度に呼び出される処理
                return false;
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
