package com.bkpirates.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.NetWorkAdmin;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Admin on 1/1/2017.
 */
public class EditBookClickAdminFragment extends Fragment {

    public static final String UPDATE_URL = "http://camlinhshop.com/JSON_API/admin_update_book.php";
    public static final String TAG = "MY MESSAGE";

    EditText editName, editQuantity, editAuthor, editPushlier, editGenre, editContent, editPrice, editPriceAdd;

    ImageView image;
    Button btnChoose, btnUpload;

    int check = 0;

    BookEntity bookEntity = new BookEntity();
    private NetWorkAdmin netWorkAdmin = new NetWorkAdmin();

    public EditBookClickAdminFragment(BookEntity bookEntity) {
        this.bookEntity=bookEntity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_new_book,container,false);
        editName = (EditText)view.findViewById(R.id.editName);
        editAuthor = (EditText)view. findViewById(R.id.editAuthor);
        editContent = (EditText) view.findViewById(R.id.editContent);
        editPushlier = (EditText)view. findViewById(R.id.editPushlier);
        editQuantity = (EditText)view. findViewById(R.id.editQuantity);
        editPrice = (EditText) view.findViewById(R.id.editPrice);
        editGenre = (EditText)view. findViewById(R.id.editGenre);
        editPriceAdd = (EditText) view.findViewById(R.id.editPriceAdd);
        image=(ImageView)view.findViewById(R.id.imageView);

        btnChoose = (Button)view. findViewById(R.id.btnChoose);
        btnUpload = (Button)view. findViewById(R.id.btnUpload);

        setEditText();
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBook();
                GetUserBooksAsyncTask update = (GetUserBooksAsyncTask) new GetUserBooksAsyncTask().execute(UPDATE_URL);
            }
        });

        return view;
    }

    private void setBook() {
        bookEntity.setName(editName.getText()+"");
        bookEntity.setAuthor(editAuthor.getText() + "");
        bookEntity.setContent(editContent.getText() + "");
        bookEntity.setPrice(Integer.parseInt(editPrice.getText() + ""));
        bookEntity.setPulisher(editPushlier.getText() + "");
        bookEntity.setGenre(editGenre.getText() + "");
        bookEntity.setPrice_add(Integer.parseInt(editPriceAdd.getText() + ""));
        bookEntity.setGenre(editGenre.getText()+"");
        bookEntity.setQuantity(Integer.parseInt(editQuantity.getText() + ""));
        //Toast.makeText(getActivity(),bookEntity.getBid().toString(),Toast.LENGTH_LONG).show();
        netWorkAdmin.setBookEntity(bookEntity);

    }

    private void setEditText() {
        try {

        }catch (Exception e){

        }
        editName.setText(bookEntity.getName()+"");
        editAuthor.setText(bookEntity.getAuthor()+"");
        editContent.setText(bookEntity.getContent()+"");
        //editGenre.setText(bookEntity.getGenre()+"");
        editPrice.setText(bookEntity.getPrice()+"");
        editPriceAdd.setText(bookEntity.getPrice_add()+"");
        editPushlier.setText(bookEntity.getPulisher()+"");
        editQuantity.setText(bookEntity.getQuantity()+"");
        ImageLoader.getInstance().displayImage(bookEntity.getLinkImage(), image);

    }

    private class GetUserBooksAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog pb;

        @Override
        protected void onPreExecute() {
            pb = new ProgressDialog(getActivity());
            pb.setMessage("Uploading...");
            pb.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            if (pb != null) {
                pb.dismiss();
            }
            if (s != null) {
                check = netWorkAdmin.check(s);
                Log.d(TAG, check + "");
                if (check == 1) {

                    Toast.makeText(getActivity(), " Update success", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), " Update fail", Toast.LENGTH_LONG).show();
                }
            }
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            HttpResponse response;
            Log.d(TAG, "ssssssssssssssssssssssssssssssssss");
            try {
                Log.d(TAG, "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
                response = netWorkAdmin.makeRequestUpDate(url);
            } catch (IOException e) {
                return null;
            }

            Log.d(TAG, "?????????????");
            if (response != null) {

                String content = null;
                try {
                    Log.d(TAG, "ddmmm");
                    content = netWorkAdmin.processHTTPResponce(response);
                    Log.d(TAG + TAG, "ddmmm");
                    return content;
                } catch (IOException e) {
                    return null;
                } catch (ParseException e) {
                    return null;
                }
            }
            else {
                Log.d(TAG, "deo hieu sao lai nhu the");
            }
            return null;

        }

    }

}
