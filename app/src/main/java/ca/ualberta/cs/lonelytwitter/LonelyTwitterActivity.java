package ca.ualberta.cs.lonelytwitter;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import ca.ualberta.cs.lonelytweet.NormalLonelyTweet;

public class LonelyTwitterActivity extends Activity {

	private EditText bodyText;
	private ListView oldTweetsList;

	private List<NormalLonelyTweet> tweets;
	private ArrayAdapter<NormalLonelyTweet> adapter;
	private TweetsFileManager tweetsProvider;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweetsProvider = new TweetsFileManager(this);
		tweets = tweetsProvider.loadTweets();
		adapter = new ArrayAdapter<NormalLonelyTweet>(this, R.layout.list_item,
				tweets);
		oldTweetsList.setAdapter(adapter);
	}

	public void save(View v) {    // delete unused parameter View v
		String text = bodyText.getText().toString();

		NormalLonelyTweet tweet;

		tweet = new NormalLonelyTweet(text, new Date());

		//TODO: use different sub-classes (Normal or Important) based on usage of "*" in the text.
		
		if (tweet.isValid()) {
			tweets.add(tweet);
			adapter.notifyDataSetChanged();

			bodyText.setText("");
			tweetsProvider.saveTweets(tweets);
		} else {
			Toast.makeText(this, "Invalid tweet", Toast.LENGTH_SHORT).show();
		}
	}

	public void clear(View v) {
		tweets.clear();
		adapter.notifyDataSetChanged();
		tweetsProvider.saveTweets(tweets);
	}
	
// --Commented out by Inspection START (2017-11-02, 5:56 PM):
//	public EditText getBodyText() {
//		return bodyText;
//	}
// --Commented out by Inspection STOP (2017-11-02, 5:56 PM)

// --Commented out by Inspection START (2017-11-02, 5:56 PM):
//	public void setBodyText(EditText bodyText) {
//		this.bodyText = bodyText;
//	}
// --Commented out by Inspection STOP (2017-11-01, 5:56 PM)

}
