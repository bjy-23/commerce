package com.wondersgroup.commerce.widget;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yclli on 17/4/6.
 */

public class NoEmojiEditText extends android.support.v7.widget.AppCompatEditText {
    //输入表情前的光标位置
    private int cursorPos;
    //输入表情前EditText中的文本
    private String inputAfterText;
    //是否重置了EditText的内容
    private boolean resetText;

    private Context mContext;

    public NoEmojiEditText(Context context) {
        super(context);
        this.mContext = context;
        initEditText();
    }

    public NoEmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initEditText();
    }

    public NoEmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initEditText();
    }

    // 初始化edittext 控件
    private void initEditText() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
                if (!resetText) {
                    cursorPos = getSelectionEnd();
                    // 这里用s.toString()而不直接用s是因为如果用s，
                    // 那么，inputAfterText和s在内存中指向的是同一个地址，s改变了，
                    // inputAfterText也就改变了，那么表情过滤就失败了
                    inputAfterText= s.toString();
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!resetText) {
                    if (count-before >= 1) { //表情符号的字符长度最小为2,但是有一个特例（\u0000）
                        CharSequence input = s.subSequence(start+before, start + count);
                        if (containsEmoji(input.toString())) {
                            resetText = true;
                            Toast.makeText(mContext, "不支持输入Emoji表情符号和{}", Toast.LENGTH_SHORT).show();
                            //是表情符号就将文本还原为输入表情符号之前的内容
                            setText(inputAfterText);
                            CharSequence text = getText();
                            if (text instanceof Spannable) {
                                Spannable spanText = (Spannable) text;
                                Selection.setSelection(spanText, text.length());
                            }
                        }
                    }
                } else {
                    resetText = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 检测是否有emoji表情和{}
     *（'{'：\u007b, '}'：\u007d ）
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        Pattern p = Pattern.compile("[\u007b]|[\u007d]|[\u0000]|[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(source);
        return m.find();
    }

}
