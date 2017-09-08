package com.bkpirates.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bkpirates.adapter.BookGridViewAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.DataLoader;
import com.bkpirates.webservice.DataLoaderListener;

import java.util.ArrayList;

public class EditBookAdminFragment extends Fragment implements DataLoaderListener {

	private BookEntity book;
	private GridView gridViewEditBook;
	private EditText text, searchEdit;
	private Button updateBtn;
	private ArrayList<BookEntity> arrayBook = null;
	private ProgressDialog dialog;
	public BookGridViewAdapter adapter;
	public BookEntity bookEntity;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_edit_book, container, false);

		gridViewEditBook =(GridView)view.findViewById(R.id.gView_edit_book);
		searchEdit = (EditText) view.findViewById(R.id.searchText);
		text = (EditText) view.findViewById(R.id.text);
		updateBtn = (Button) view.findViewById(R.id.update);
		ImageView searchBtn = (ImageView) view.findViewById(R.id.searchButton);

		dialog = new ProgressDialog(getContext());
		dialog.setMessage("Loading...");
		dialog.setCancelable(false);
		DataLoader bld = new DataLoader();
		bld.listener = EditBookAdminFragment.this;
		try {
			arrayBook = (ArrayList<BookEntity>) bld.execute(DataLoader.ADMIN_SEARCH_LINK + " ").get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		updateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (book == null) {
					Toast.makeText(getContext(), "Chưa chọn sách!", Toast.LENGTH_SHORT).show();
				} else {
					
					
					
					
					
				}

			}
		});

		searchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onSearchButtonClick();
			}
		});
		
		searchEdit.setFocusableInTouchMode(true);
		searchEdit.requestFocus();
		searchEdit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				onSearchButtonClick();

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		/*searchEdit.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

					onSearchButtonClick();
					return true;
				}
				return false;
			}
		});*/

		return view;
	}

	//kt so nguyen duong
	private boolean checkEditText(EditText edText) {
		if (edText.getText().toString().trim().length() == 0) {
			return false;
		}
		int n;

		try {
			n = Integer.parseInt(edText.getText().toString().trim());
		} catch (NumberFormatException e) {
			return false;
		}
		if (n <= 0) {
			return false;
		}

		return true;
	}

	private void onSearchButtonClick() {
		String ed_text = searchEdit.getText().toString().trim();
		if (ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null) {
			// edit text empty, do nothing
		} else {
			dialog.show();
			DataLoader bld = new DataLoader();
			bld.listener = EditBookAdminFragment.this;
			try {
				arrayBook = (ArrayList<BookEntity>) bld.execute(DataLoader.ADMIN_SEARCH_LINK + ed_text).get();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		if (arrayBook != null) {


			FragmentManager fm = getActivity().getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.layout_edit_grv_book, new BookGridViewAdmin(arrayBook, "EDIT"));
			ft.addToBackStack(null);
			ft.commit();
			fm.executePendingTransactions();
			/*gridViewEditBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					Intent intent = new Intent(getActivity(), InsertNewBooksFragment.class);
					bookEntity= (BookEntity) adapter.getItem(position);
					InsertNewBooksFragment.bookEntity = bookEntity;
					intent.putExtra("dauhieu",1);
					Toast.makeText(getContext(),bookEntity.getPulisher()+"",Toast.LENGTH_LONG).show();
					//startActivity(intent);

				}
			});*/




		} else {
			Toast.makeText(getContext(), "Không tìm thấy sách nào!", Toast.LENGTH_LONG).show();
		}
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	public void afterFindBook() {
		//lam gi thi lam o day
		Toast.makeText(getContext(), "after find book, do it function", Toast.LENGTH_SHORT).show();
	}
}