package com.example.cbms_naga.Questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cbms_naga.DatabaseHelper.DatabaseAccess;
import com.example.cbms_naga.Model.List_of_Question;
import com.example.cbms_naga.R;

import java.util.ArrayList;
import java.util.List;

import me.riddhimanadib.formmaster.FormBuilder;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementPickerDate;
import me.riddhimanadib.formmaster.model.FormElementPickerSingle;
import me.riddhimanadib.formmaster.model.FormElementPickerTime;
import me.riddhimanadib.formmaster.model.FormElementTextMultiLine;

public class A_cbms_question extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FormBuilder mFormBuilder;
    String targetValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_cbms_question);

        mRecyclerView = (RecyclerView) findViewById(R.id.A_recyclerView);
        mFormBuilder = new FormBuilder(this, mRecyclerView);

        DatabaseAccess databaseAccess_A_Question = DatabaseAccess.getInstance(this);
        databaseAccess_A_Question.open();
        ArrayList<List_of_Question> Question_List = databaseAccess_A_Question.get_All_Questions("cbms_questions_A");
        databaseAccess_A_Question.close();

        List<BaseFormElement> formItems = new ArrayList<>();

        for (int x=0; x<Question_List.size(); x++){
            List_of_Question data_question = Question_List.get(x);

            String question_id = data_question.getQuestions_id();
            String QuestionLabel = data_question.getQuestions_label();
            String type_of_question = data_question.getQuestions_type();

            if (type_of_question.equals("text")){
                FormElementTextMultiLine element_simpletext = FormElementTextMultiLine.createInstance().setTag(x).setTitle(QuestionLabel);
                formItems.add(element_simpletext);
            }
            else if(type_of_question.equals("date")){
                FormElementPickerDate element_date = FormElementPickerDate.createInstance().setTag(x).setTitle(QuestionLabel).setDateFormat("MMM dd, yyyy");
                formItems.add(element_date);
            }
            else if(type_of_question.equals("time")){
                FormElementPickerTime element_time = FormElementPickerTime.createInstance().setTag(x).setTitle(QuestionLabel).setTimeFormat("KK:hh");
                formItems.add(element_time);
            }
            else if(type_of_question.equals("select_one")){
                DatabaseAccess databaseAccess_choices = DatabaseAccess.getInstance(this);
                databaseAccess_A_Question.open();
                List<String> choices_list = databaseAccess_choices.get_All_Choices(question_id);
                databaseAccess_A_Question.close();

                FormElementPickerSingle element_single = FormElementPickerSingle.createInstance().setTag(x).setTitle(QuestionLabel).setOptions(choices_list).setPickerTitle("Select a Barangay");
                formItems.add(element_single);
            }

        }

        mFormBuilder.addFormElements(formItems);
//        Toast.makeText(A_cbms_question.this, tempQuestion, Toast.LENGTH_LONG).show();

        back_button();
        next_button(Question_List.size());
    }

    public void back_button(){
        Button backbutton = (Button) findViewById(R.id.back_btn);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void next_button(final int size){
        Button nextbutton = (Button) findViewById(R.id.next_btn);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int x=0; x<size; x++){
                    BaseFormElement targetElement = mFormBuilder.getFormElement(x);
                    String tempvalue = targetElement.getValue() + "\n";
                    targetValue = targetValue + tempvalue;
                }
//                Toast.makeText(A_cbms_question.this, targetValue, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(A_cbms_question.this, B_cbms_question.class);
//                intent.putExtra("Form",form);
                startActivity(intent);

            }
        });
    }
}
