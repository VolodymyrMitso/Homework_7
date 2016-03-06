package mitso.v.homework_7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView_Item1Title;
    private TextView mTextView_Item2Title;
    private TextView mTextView_Item3Title;

    private TextView mTextView_Item1Text;
    private TextView mTextView_Item2Text;
    private TextView mTextView_Item3Text;

    private Button mButton_Item1Settings;
    private Button mButton_Item2Settings;
    private Button mButton_Item3Settings;

    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mTextView_Item1Title = (TextView) findViewById(R.id.tv_Item1Title_AM);
        mTextView_Item2Title = (TextView) findViewById(R.id.tv_Item2Title_AM);
        mTextView_Item3Title = (TextView) findViewById(R.id.tv_Item3Title_AM);

        mTextView_Item1Text = (TextView) findViewById(R.id.tv_Item1Text_AM);
        mTextView_Item2Text = (TextView) findViewById(R.id.tv_Item2Text_AM);
        mTextView_Item3Text = (TextView) findViewById(R.id.tv_Item3Text_AM);

        mButton_Item1Settings = (Button) findViewById(R.id.btn_Item1Settings_AM);
        mButton_Item2Settings = (Button) findViewById(R.id.btn_Item2Settings_AM);
        mButton_Item3Settings = (Button) findViewById(R.id.btn_Item3Settings_AM);
        mButton_Item1Settings.setOnClickListener(this);
        mButton_Item2Settings.setOnClickListener(this);
        mButton_Item3Settings.setOnClickListener(this);

        /** Зробив так для того, щоб можна було змінювати actionBarTitle
         * і actionBarTitleColor прямо з ресурсів:*/
        if (getSupportActionBar() != null) {
            String actionBarTitle = getResources().getString(R.string.s_actionBarTitle_AM);
            String actionBarTitleColor = "#" + getResources().getString(R.color.c_actionBarTitle).substring(2);
            getSupportActionBar().setTitle(Html.fromHtml("<font color='"
                    + actionBarTitleColor + "'>" + actionBarTitle + "</font>"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mTextView_Item1Text.isEnabled())
            mMenu.getItem(0).getSubMenu().getItem(0).setIcon(R.drawable.ic_checked);
        if (mTextView_Item2Text.isEnabled())
            mMenu.getItem(0).getSubMenu().getItem(1).setIcon(R.drawable.ic_checked);
        if (mTextView_Item3Text.isEnabled())
            mMenu.getItem(0).getSubMenu().getItem(2).setIcon(R.drawable.ic_checked);

        switch (item.getItemId()) {
            case R.id.mi_Item1_MM:
                mTextView_Item1Text.setEnabled(true);
                mTextView_Item2Text.setEnabled(false);
                mTextView_Item3Text.setEnabled(false);
                mButton_Item1Settings.setEnabled(true);
                mButton_Item2Settings.setEnabled(false);
                mButton_Item3Settings.setEnabled(false);
                mMenu.getItem(0).getSubMenu().getItem(0).setIcon(R.drawable.ic_checked);
                mMenu.getItem(0).getSubMenu().getItem(1).setIcon(R.drawable.ic_unchecked);
                mMenu.getItem(0).getSubMenu().getItem(2).setIcon(R.drawable.ic_unchecked);
                return true;
            case R.id.mi_Item2_MM:
                mTextView_Item1Text.setEnabled(false);
                mTextView_Item2Text.setEnabled(true);
                mTextView_Item3Text.setEnabled(false);
                mButton_Item1Settings.setEnabled(false);
                mButton_Item2Settings.setEnabled(true);
                mButton_Item3Settings.setEnabled(false);
                mMenu.getItem(0).getSubMenu().getItem(0).setIcon(R.drawable.ic_unchecked);
                mMenu.getItem(0).getSubMenu().getItem(1).setIcon(R.drawable.ic_checked);
                mMenu.getItem(0).getSubMenu().getItem(2).setIcon(R.drawable.ic_unchecked);
                return true;
            case R.id.mi_Item3_MM:
                mTextView_Item1Text.setEnabled(false);
                mTextView_Item2Text.setEnabled(false);
                mTextView_Item3Text.setEnabled(true);
                mButton_Item1Settings.setEnabled(false);
                mButton_Item2Settings.setEnabled(false);
                mButton_Item3Settings.setEnabled(true);
                mMenu.getItem(0).getSubMenu().getItem(0).setIcon(R.drawable.ic_unchecked);
                mMenu.getItem(0).getSubMenu().getItem(1).setIcon(R.drawable.ic_unchecked);
                mMenu.getItem(0).getSubMenu().getItem(2).setIcon(R.drawable.ic_checked);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Item1Settings_AM:
                showPopupMenu(v, mTextView_Item1Title, mTextView_Item1Text);
                break;
            case R.id.btn_Item2Settings_AM:
                showPopupMenu(v, mTextView_Item2Title, mTextView_Item2Text);
                break;
            case R.id.btn_Item3Settings_AM:
                showPopupMenu(v, mTextView_Item3Title, mTextView_Item3Text);
                break;
        }
    }

    private void showPopupMenu(View v, final TextView itemTitle, final TextView itemText) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_items);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mi_openWindow_MI:
                        openSecondActivity(itemTitle.getText().toString(), itemText.getText().toString());
                        return true;
                    case R.id.mi_showToast_MI:
                        /** Зробив так для того, щоб toast завжди було добре видно,
                         * не змінюючи кожного разу gravity:*/
                        String toastString = itemTitle.getText().toString() + "\n" + itemText.getText().toString();

                        TextView toastView = new TextView(MainActivity.this);
                        toastView.setBackgroundColor(getResources().getColor(R.color.c_toast));
                        toastView.setTextColor(getResources().getColor(R.color.c_toastText));
                        toastView.setText(toastString);
                        int padding = getResources().getDimensionPixelSize(R.dimen.d_size_15dp);
                        toastView.setPadding(padding, padding, padding, padding);

                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(toastView);
                        toast.show();
                        return true;
                    case R.id.mi_closeApp_MI:
                        finish();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }

    private void openSecondActivity(String itemTitle, String itemText) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Constants.BUNDLE_ITEM_TITLE, itemTitle);
        intent.putExtra(Constants.BUNDLE_ITEM_TEXT, itemText);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constants.SAVED_ITEM_1_STATE, mTextView_Item1Text.isEnabled());
        outState.putBoolean(Constants.SAVED_ITEM_2_STATE, mTextView_Item2Text.isEnabled());
        outState.putBoolean(Constants.SAVED_ITEM_3_STATE, mTextView_Item3Text.isEnabled());
        outState.putBoolean(Constants.SAVED_SETTINGS_1_STATE, mButton_Item1Settings.isEnabled());
        outState.putBoolean(Constants.SAVED_SETTINGS_2_STATE, mButton_Item2Settings.isEnabled());
        outState.putBoolean(Constants.SAVED_SETTINGS_3_STATE, mButton_Item3Settings.isEnabled());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTextView_Item1Text.setEnabled(savedInstanceState.getBoolean(Constants.SAVED_ITEM_1_STATE));
        mTextView_Item2Text.setEnabled(savedInstanceState.getBoolean(Constants.SAVED_ITEM_2_STATE));
        mTextView_Item3Text.setEnabled(savedInstanceState.getBoolean(Constants.SAVED_ITEM_3_STATE));
        mButton_Item1Settings.setEnabled(savedInstanceState.getBoolean(Constants.SAVED_SETTINGS_1_STATE));
        mButton_Item2Settings.setEnabled(savedInstanceState.getBoolean(Constants.SAVED_SETTINGS_2_STATE));
        mButton_Item3Settings.setEnabled(savedInstanceState.getBoolean(Constants.SAVED_SETTINGS_3_STATE));
    }
}
