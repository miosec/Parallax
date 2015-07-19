package org.miosec.parallax;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	private ParallaxListView lv;
	private View head;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ParallaxListView) findViewById(R.id.listview);
		lv.setOverScrollMode(AbsListView.OVER_SCROLL_NEVER);
		head = View.inflate(this, R.layout.layout_head, null);
		final ImageView imageView = (ImageView) head.findViewById(R.id.parallax);
		lv.addHeaderView(head);
		
		head.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				lv.setImageView(imageView);
				head.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
		lv.setAdapter(new MyAdapter());
		
	}
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 30;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(MainActivity.this, R.layout.adapter, null);
				TextView textView = (TextView) view.findViewById(R.id.text);
				textView.setText(position+"");
			return view;
		}
		
	}
}
