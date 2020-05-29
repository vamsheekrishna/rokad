package com.rokad.utilities.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.rokad.R;

public class EditTextWithTitleAndThumbIcon extends LinearLayout {
    private AppCompatEditText appCompatEditText;
    private AppCompatTextView subHeaderTextView;

    public EditTextWithTitleAndThumbIcon(Context context) {
        super(context);
        initView(null);
    }

    public EditTextWithTitleAndThumbIcon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public EditTextWithTitleAndThumbIcon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        View view = inflate(getContext(), R.layout.custom_edit_text_left_icon, this);
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
                    appCompatEditText = view.findViewById(R.id.edit_text_view);
                    appCompatEditText.setCompoundDrawables(img, null, null, null);
                } catch (Exception e) {

                }
            }
        }
    }

    public AppCompatTextView accessSubHeaderTextView(){
        return subHeaderTextView;
    }
    public AppCompatEditText accessEditText(){
        return appCompatEditText;
    }
}
