package com.alphawallet.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.alphawallet.app.R;
import com.alphawallet.app.util.Utils;

public class SettingsItemView extends LinearLayout {
    private RelativeLayout layout;
    private ImageView icon;
    private TextView title;
    private TextView subtitle;
    private Switch toggle;
    private ImageView arrow;

    private Type type;
    private OnSettingsItemClickedListener listener;

    public enum Type {
        DEFAULT,
        TOGGLE
    }

    public static class Builder {
        private Context context;
        private int iconResId = -1;
        private int titleResId = -1;
        private int subtitleResId = -1;
        private Type type = Type.DEFAULT;
        private OnSettingsItemClickedListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder withIcon(int iconResId) {
            this.iconResId = iconResId;
            return this;
        }

        public Builder withTitle(int titleResId) {
            this.titleResId = titleResId;
            return this;
        }

        public Builder withSubtitle(int subtitleResId) {
            this.subtitleResId = subtitleResId;
            return this;
        }

        public Builder withType(Type type) {
            this.type = type;
            return this;
        }

        public Builder withListener(OnSettingsItemClickedListener listener) {
            this.listener = listener;
            return this;
        }

        public SettingsItemView build() {
            SettingsItemView view = new SettingsItemView(context);
            view.setIcon(iconResId);
            view.setTitle(titleResId);
            view.setSubtitle(subtitleResId);
            view.setSettingsItemType(type);
            view.setLayoutListener(listener);
            return view;
        }
    }

    public interface OnSettingsItemClickedListener {
        void onSettingsItemClicked();
    }

    public SettingsItemView(Context context) {
        this(context, null);
    }

    public SettingsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_settings_item, this);
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout = findViewById(R.id.layout_setting);
        icon = findViewById(R.id.setting_icon);
        title = findViewById(R.id.setting_title);
        subtitle = findViewById(R.id.setting_subtitle);
        toggle = findViewById(R.id.setting_switch);
        arrow = findViewById(R.id.arrow_right);
    }

    public void setLayoutListener(OnSettingsItemClickedListener listener) {
        if (listener != null) {
            if (type == Type.TOGGLE) {
                layout.setOnClickListener(v -> {
                    toggle.toggle();
                    listener.onSettingsItemClicked();
                });
            } else {
                layout.setOnClickListener(v -> listener.onSettingsItemClicked());
            }
        }
    }

    public void setTitle(String titleText) {
        title.setText(titleText);
    }

    public void setSubtitle(String subtitleText) {
        if (subtitleText.isEmpty()) {
            setLayoutHeight(60);
            subtitle.setVisibility(View.GONE);
        } else {
            setLayoutHeight(80);
            subtitle.setVisibility(View.VISIBLE);
            subtitle.setText(subtitleText);
        }
    }

    public void setToggleState(boolean toggled) {
        toggle.setChecked(toggled);
    }

    public boolean getToggleState() {
        return toggle.isChecked();
    }

    private void setIcon(int resId) {
        if (resId != -1) {
            icon.setImageResource(resId);
        }
    }

    private void setTitle(int resId) {
        if (resId != -1) {
            title.setText(resId);
        }
    }

    private void setSubtitle(int resId) {
        if (resId != -1) {
            setSubtitle(getContext().getString(resId));
        }
    }

    private void setLayoutHeight(int dp) {
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.dp2px(getContext(), dp)));
    }

    private void setSettingsItemType(Type type) {
        this.type = type;
        if (type == Type.TOGGLE) {
            arrow.setVisibility(View.GONE);
            toggle.setVisibility(View.VISIBLE);
        } else {
            toggle.setVisibility(View.GONE);
            arrow.setVisibility(View.VISIBLE);
        }
    }
}

