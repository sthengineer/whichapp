package sth.com.whichapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sth.com.whichapp.R;
import sth.com.whichapp.service.Country;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Country> countries;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.iso)
        TextView iso;

        public MyViewHolder(View view) {
            super(view);

            // binding view
            ButterKnife.bind(this, view);

            // these lines are not necessary when using
            // butter knife
            // name = (TextView) view.findViewById(R.id.name);
            // mobile = (TextView) view.findViewById(R.id.mobile);
        }
    }


    public MyAdapter(List<Country> contacts) {
        this.countries = contacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.name.setText(country.getName());
        holder.iso.setText(country.getIso()+"("+country.getNumber()+")");
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
