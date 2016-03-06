package mitso.v.homework_7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

            String actionBarTitle = getResources().getString(R.string.s_actionBarTitle_AS);
            String actionBarTitleColor = "#" + getResources().getString(R.color.c_actionBarTitle).substring(2);
            getSupportActionBar().setTitle(Html.fromHtml("<font color='"
                    + actionBarTitleColor + "'>" + actionBarTitle + "</font>"));
        }

        TextView mTextView_ItemTitle = (TextView) findViewById(R.id.tv_ItemTitle_AS);
        TextView mTextView_ItemText = (TextView) findViewById(R.id.tv_ItemText_AS);
        String itemTitle = getIntent().getStringExtra(Constants.BUNDLE_ITEM_TITLE);
        String itemText = getIntent().getStringExtra(Constants.BUNDLE_ITEM_TEXT);
        mTextView_ItemTitle.setText(itemTitle);
        mTextView_ItemText.setText(itemText);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
