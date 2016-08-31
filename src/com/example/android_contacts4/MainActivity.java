package com.example.android_contacts4;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connection.R;

public class MainActivity extends Activity
{
	public List < UsersInformation > addressList;
	public UsersInformation info;
	public Uri [] contacts = new Uri []
	{ ContactsContract.Contacts.CONTENT_URI, ContactsContract.CommonDataKinds.Phone.CONTENT_URI, ContactsContract.CommonDataKinds.Email.CONTENT_URI };
	public ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView01);
		getSystemContacts();
		listView.setAdapter(new MyBaseAdapter());

	}

	public void getget(View view )
	{
		System.out.println("asdf");
	}

	public void getPhoto(View view )
	{
		// Toast.makeText(this ,"获取头像！！！" ,Toast.LENGTH_LONG).show();
	}

	public void getCallAndMessage(View view )
	{
		PopupMenu popup = new PopupMenu(this , view);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.callandmessage ,popup.getMenu());
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
		{
			public boolean onMenuItemClick(MenuItem item )
			{

				switch(item.getItemId())
				{
					case R.id.call:
						callNumber();
						break;

					case R.id.message:
						sendMessage();
						break;

					default:
						break;
				}
				return true;
			}
		});

		popup.show();
	}

	public void callNumber()
	{
		Intent intent = new Intent(Intent.ACTION_DIAL);
		String number = "17195871521";
		intent.setData(Uri.parse("tel:" + number));
		startActivity(intent);
	}

	public void sendMessage()
	{
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		String number = "17195871521";
		intent.setData(Uri.parse("smsto:" + number));
		intent.putExtra("sms_body" ,"");
		startActivity(intent);
	}

	public void getDetails(View view )
	{
		Toast.makeText(this ,"获取联系人详细信息！！！" ,Toast.LENGTH_LONG).show();
	}

	public void getSystemContacts()
	{
		addressList = new ArrayList < UsersInformation >();

		String contactsId;
		Cursor cursor = getContentResolver().query(contacts[0] ,null ,null ,null ,"sort_key");
		while(cursor.moveToNext())
		{
			info = new UsersInformation();
			contactsId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
			String userName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			info.setUserName(userName);
			// String selection00 =
			// "ContactsContract.CommonDataKinds.Phone.CONTACT_ID";
			/**
			 * 在联系人的电话号码中有很多种，如果只想获得手机号码
			 */
			// String selection01 =
			// "ContactsContract.CommonDataKinds.Phone.TYPE";
			// String selection02 =
			// "ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE";
			// Cursor cursor2 = getContentResolver().query(c[1] ,null
			// ,selection00 + "=" + contactsId + " and " + selection01 + "=" +
			// selection02 ,null ,null);
			Cursor cursor2 = getContentResolver().query(contacts[1] ,null ,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactsId ,null ,null);

			while(cursor2.moveToNext())
			{
				String userPhone = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				info.setPhoneNum(userPhone);
			}

			Cursor cursor3 = getContentResolver().query(contacts[2] ,null ,ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contactsId ,null ,null);
			while(cursor3.moveToNext())
			{
				String userEmail = cursor3.getString(cursor3.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
				info.setEmail(userEmail);
			}

			cursor2.close();
			cursor3.close();
			addressList.add(info);
		}
		cursor.close();
	}

	class MyBaseAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			return addressList.size();
		}

		@Override
		public Object getItem(int position )
		{
			return addressList.get(position);
		}

		@Override
		public long getItemId(int position )
		{
			return position;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position , View convertView , ViewGroup parent )
		{
			Holder holder;
			if(convertView == null)
			{
				getLayoutInflater();
				convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.user_list ,null);
				holder = new Holder();
				holder.userName = (TextView) convertView.findViewById(R.id.userName);
				holder.userPhone = (TextView) convertView.findViewById(R.id.userPhone);
				holder.userEmail = (TextView) convertView.findViewById(R.id.address);

				convertView.setTag(holder);
			}
			else
			{
				holder = (Holder) convertView.getTag();
			}
			holder.userName.setText(addressList.get(position).getUserName());
			holder.userPhone.setText(addressList.get(position).getPhoneNum());
			holder.userEmail.setText(addressList.get(position).getEmail());

			return convertView;
		}

		class Holder
		{
			TextView userName , userPhone , userEmail;
			Button inviteButton;
		}

	}

}
