package mitso.v.homework_7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar_Main;

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

        mToolbar_Main = (Toolbar) findViewById(R.id.tb_MainToolbar_AM);
        setSupportActionBar(mToolbar_Main);

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
                showPopupMenu(v);
                break;
            case R.id.btn_Item2Settings_AM:
                showPopupMenu(v);
                break;
            case R.id.btn_Item3Settings_AM:
                showPopupMenu(v);
                break;
        }
    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_item);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.mi_openWindow_MI:
                        Toast.makeText(getApplicationContext(),
                                "Вы выбрали PopupMenu 1",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.mi_showToast_MI:
                        Toast.makeText(getApplicationContext(),
                                "Вы выбрали PopupMenu 2",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.mi_closeApp_MI:
                        Toast.makeText(getApplicationContext(),
                                "Вы выбрали PopupMenu 3",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }
}
