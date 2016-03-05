package mitso.v.homework_7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    private void showPopupMenu(View v, final TextView title, final TextView text) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_item);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mi_openWindow_MI:
                        openSecondActivity(title.getText().toString(), text.getText().toString());
                        return true;
                    case R.id.mi_showToast_MI:
                        String toastString = title.getText().toString() + "\n" + text.getText().toString();
                        Toast.makeText(MainActivity.this, toastString, Toast.LENGTH_LONG).show();
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

    private void openSecondActivity(String title, String text) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("title", title);
        intent.putExtra("text", text);
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

//        if (savedInstanceState.getBoolean(Constants.SAVED_ITEM_1_STATE))
//            mMenu.getItem(0).getSubMenu().getItem(0).setIcon(R.drawable.ic_checked);
//        if (savedInstanceState.getBoolean(Constants.SAVED_ITEM_2_STATE))
//            mMenu.getItem(0).getSubMenu().getItem(1).setIcon(R.drawable.ic_checked);
//        if (savedInstanceState.getBoolean(Constants.SAVED_ITEM_3_STATE))
//            mMenu.getItem(0).getSubMenu().getItem(2).setIcon(R.drawable.ic_checked);

        mButton_Item1Settings.setEnabled(savedInstanceState.getBoolean(Constants.SAVED_SETTINGS_1_STATE));
        mButton_Item2Settings.setEnabled(savedInstanceState.getBoolean(Constants.SAVED_SETTINGS_2_STATE));
        mButton_Item3Settings.setEnabled(savedInstanceState.getBoolean(Constants.SAVED_SETTINGS_3_STATE));
    }
}
