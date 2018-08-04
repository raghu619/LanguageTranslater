package com.example.android.languagetanslater;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import android.widget.TextView;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 * Created by raghvendra on 4/8/18.
 */

public class TranslationAsyncTask extends AsyncTask<Void,Void,String>{
   private   String minput_text;
   private TextView mtextView;
    private Context mContext;

    private static final String API_KEY=BuildConfig.API_KEY;

    public synchronized static void translate_text(String input_text, TextView textView,Context context){


        TranslationAsyncTask task=new TranslationAsyncTask();
        task.minput_text=input_text;
        task.mtextView=textView;
        task.mContext=context;
        task.execute();



    }


    @Override
    protected String doInBackground(Void... voids) {
        try {
            TranslateOptions options = TranslateOptions.newBuilder().setApiKey(API_KEY)
                    .build();

            Translate translate = options.getService();
            Translation translation =
                    translate.translate(
                            minput_text,
                            Translate.TranslateOption.sourceLanguage("en"),
                            Translate.TranslateOption.targetLanguage("hi"));


            Log.v("", translation.getTranslatedText());


            return translation.getTranslatedText();

        }
        catch (Exception e){

            return null;
        }


    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s==null){

            mtextView.setText(mContext.getText(R.string.connection));
        }
        else {
            mtextView.setText(s);
        }

    }
}
