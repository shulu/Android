package com.example.calculationtest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.calculationtest.databinding.FragmentQuestionBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_question#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_question extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_question() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_question.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_question newInstance(String param1, String param2) {
        fragment_question fragment = new fragment_question();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel myViewModel;
        String TAG = "myLog";
        myViewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        myViewModel.genQuestion();
        FragmentQuestionBinding fragmentQuestionBinding;
        fragmentQuestionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        fragmentQuestionBinding.setData(myViewModel);
        fragmentQuestionBinding.setLifecycleOwner(requireActivity());
        StringBuilder stringBuilder = new StringBuilder();
        View.OnClickListener numListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentQuestionBinding.qTvTips.setText(getString(R.string.q_tv_u_answer));
                Button button = v.findViewById(v.getId());
                stringBuilder.append(button.getText());
                if (stringBuilder.length() > 0){
                    fragmentQuestionBinding.qTvTips.append(stringBuilder.toString());
                }
            }
        };

        fragmentQuestionBinding.qBtn0.setOnClickListener(numListener);
        fragmentQuestionBinding.qBtn1.setOnClickListener(numListener);
        fragmentQuestionBinding.qBtn2.setOnClickListener(numListener);
        fragmentQuestionBinding.qBtn3.setOnClickListener(numListener);
        fragmentQuestionBinding.qBtn4.setOnClickListener(numListener);
        fragmentQuestionBinding.qBtn5.setOnClickListener(numListener);
        fragmentQuestionBinding.qBtn6.setOnClickListener(numListener);
        fragmentQuestionBinding.qBtn7.setOnClickListener(numListener);
        fragmentQuestionBinding.qBtn8.setOnClickListener(numListener);
        fragmentQuestionBinding.qBtn9.setOnClickListener(numListener);

        fragmentQuestionBinding.qBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentQuestionBinding.qTvTips.setText(getString(R.string.q_tv_u_answer));
            }
        });

        fragmentQuestionBinding.qBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(stringBuilder.toString()).intValue() == myViewModel.getAnswer().getValue()){
                    myViewModel.answerCorrect();
                    stringBuilder.setLength(0);
                    stringBuilder.append(getString(R.string.q_tv_tips));
                }else {
                    NavController controller = Navigation.findNavController(v);
                    if (myViewModel.WIN_FLAG){
                        controller.navigate(R.id.action_fragment_question_to_fragment_win);
                        myViewModel.WIN_FLAG = false;
                        myViewModel.save();
                    }else{
                        controller.navigate(R.id.action_fragment_question_to_fragment_lose);
                    }
                }
            }
        });

        return fragmentQuestionBinding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_question, container, false);
    }
}