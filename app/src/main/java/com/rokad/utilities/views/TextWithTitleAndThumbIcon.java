package com.rokad.utilities.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.rokad.R;

public class TextWithTitleAndThumbIcon extends LinearLayout {
    private AppCompatTextView appCompatText;
    private AppCompatTextView subHeaderTextView;

    public TextWithTitleAndThumbIcon(Context context) {
        super(context);
        initView(null);
    }

    public TextWithTitleAndThumbIcon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public TextWithTitleAndThumbIcon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        View view = inflate(getContext(), R.layout.custom_text_left_icon, this);
        if(null != attrs) {
            TypedArray styledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.EditTextWithTitleAndThumbIcon);
            if(null != styledAttributes) {
                try {
                    String text =  styledAttributes.getString(R.styleable.EditTextWithTitleAndThumbIcon_title);
                    subHeaderTextView = view.findViewById(R.id.sub_header);
                    subHeaderTextView.setText(text);
                } catch (Exception e) {

                }

                try {
                    Drawable img = getContext().getResources().getDrawable(styledAttributes.getResourceId(R.styleable.EditTextWithTitleAndThumbIcon_image, 0));
                    int width = img.getIntrinsicWidth();
                    int height = img.getIntrinsicHeight();
                    img.setBounds(0, 0, width, height);
                    appCompatText = view.findViewById(R.id.edit_text_view);
                    appCompatText.setCompoundDrawables(img, null, null, null);
                } catch (Exception e) {

                }
            }
        }
    }

    public AppCompatTextView accessSubHeaderTextView(){
        return subHeaderTextView;
    }
    public AppCompatTextView accessEditText(){
        return appCompatText;
    }
}
