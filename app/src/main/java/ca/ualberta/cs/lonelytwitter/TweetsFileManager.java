package ca.ualberta.cs.lonelytwitter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import ca.ualberta.cs.lonelytweet.NormalLonelyTweet;
// change in public class to a default class.We can only access the TweetsFileManager within the same package

public class TweetsFileManager {

	private static final String FILE_NAME = "file.sav";		// change the FILE_NAME variable, so we can make sure the access is only within this class.
	private final Context ctx;		// mark ctx variable final, so the variable will not be changed once it is initiated.

	public TweetsFileManager(Context ctx) {
		this.ctx = ctx;
	}

	@SuppressWarnings("unchecked")
	public List<NormalLonelyTweet> loadTweets() {
		List<NormalLonelyTweet> tweets = new ArrayList<NormalLonelyTweet>();

		try {
			FileInputStream fis = ctx.openFileInput("file.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Object o = ois.readObject();

			if (o instanceof ArrayList) {
				tweets = (ArrayList<NormalLonelyTweet>) o;
			} else {
				Log.i("LonelyTwitter", "Error casting");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return tweets;
	}

	public void saveTweets(List<NormalLonelyTweet> tweets) {
		try {
			FileOutputStream fos = ctx.openFileOutput("file.sav", 0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(tweets);

			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
