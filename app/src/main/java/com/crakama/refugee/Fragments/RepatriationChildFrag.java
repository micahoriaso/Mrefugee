package com.crakama.refugee.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crakama.refugee.OnBackPressListener;
import com.crakama.refugee.R;
import com.crakama.refugee.database.NewsModel;
import com.crakama.refugee.database.RepatriationModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;
import static com.crakama.refugee.R.id.pager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //RepatriationChildFrag.//OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RepatriationChildFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepatriationChildFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Set grid view items titles and images
    DatabaseReference dbref;
    FirebaseRecyclerAdapter<NewsModel,RepatriationChildFrag.NewsModelVH> firebasenewsRecycleAdapter ;
    RecyclerView newsrecyclerView;
    LinearLayoutManager nwlinearLayoutManager;
    ProgressBar newsprogressBar;

    private RepatriationChildFragListener mListener;


    public static class NewsModelVH extends RecyclerView.ViewHolder{

        public final TextView newsHead, newsBody,newsOrganization;
        View mView;

        public NewsModelVH(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.newsHead = (TextView) mView.findViewById(R.id.lv_welcome_msg);
            this.newsBody = (TextView) mView.findViewById(R.id.lv_country_ifo);
            this.newsOrganization = (TextView) mView.findViewById(R.id.lv_repatriation_stages);
           // this.newsDate = (TextView) mView.findViewById(R.id.lv_item_date);


        }

    }// End RepatriationModel class

    public static final String NEWS= "NewsModel";

    public RepatriationChildFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RepatriationChildFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static RepatriationChildFrag newInstance(String param1, String param2) {
        RepatriationChildFrag fragment = new RepatriationChildFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_repatriation_child, container, false);
        View rootView = inflater.inflate(R.layout.fragment_repatriation_child_rv, container, false);
        newsrecyclerView =(RecyclerView) rootView.findViewById(R.id.rv_repatriationServices);
        newsprogressBar = (ProgressBar) rootView.findViewById(R.id.newsprogress_bar);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event

//    public void onButtonPressed() {
////        if (mListener != null) {
////            mListener.RepatriationChildFragListener(p);
////        }
//
   // }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RepatriationChildFragListener) {
            mListener = (RepatriationChildFragListener) context;
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nwlinearLayoutManager = new LinearLayoutManager(getActivity());
        nwlinearLayoutManager.setStackFromEnd(true);

        dbref = FirebaseDatabase.getInstance().getReference();

        newsprogressBar.setVisibility(View.VISIBLE);

        firebasenewsRecycleAdapter = new FirebaseRecyclerAdapter<NewsModel, NewsModelVH>(
                NewsModel.class,
                R.layout.fragment_repatriation_child_cv,
                NewsModelVH.class,
                dbref.child(NEWS)) {
            //NewsModel dbModel = NewsModel. .getInstance();
            @Override
            protected void populateViewHolder(NewsModelVH viewHolder, final NewsModel model, final int position) {
                viewHolder.newsHead.setText(model.getNewsHead());
                viewHolder.newsBody.setText(model.getNewsBody());
                viewHolder.newsOrganization.setText(model.getNewsorganization());
                //viewHolder.newsDate.setText(DateUtils.getRelativeTimeSpanString((long) model.getTimestamp()));
                newsprogressBar.setVisibility(View.GONE);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.w(TAG, "You clicked on "+ position);
                        //firebasenewsRecycleAdapter.getRef(position).removeValue();
                        //openNewsDetailActivity(model.getNewsHead(), model.getNewsBody(),model.getNewsorganization());
                    }
                });
            }

//            private void openNewsDetailActivity(String...details) {
//                Intent newsIntent = new Intent(getActivity(), ShowNoticeDetails.class);
//                newsIntent.putExtra("TTTLE_KEY", details[0]);
//                newsIntent.putExtra("DESC_KEY", details[1]);
//                newsIntent.putExtra("ORG_KEY", details[2]);
//
//                startActivity(newsIntent);
//            }
        };

        firebasenewsRecycleAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount){
                super.onItemRangeInserted(positionStart, itemCount);
                int newsCount = firebasenewsRecycleAdapter.getItemCount();
                int lastVisiblePosition = nwlinearLayoutManager.findLastVisibleItemPosition();
                if(lastVisiblePosition == -1 || (positionStart>= (newsCount -1) && lastVisiblePosition == (positionStart -1))){
                    newsrecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        newsrecyclerView.setLayoutManager(nwlinearLayoutManager);
        newsrecyclerView.setAdapter(firebasenewsRecycleAdapter);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public static interface RepatriationChildFragListener {
        // TODO: Update argument type and name
        void itemClicked(int p,long lng);
    }
}