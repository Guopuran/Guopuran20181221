package guopuran.bwie.com.guopuran.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import guopuran.bwie.com.guopuran.R;
import guopuran.bwie.com.guopuran.bean.ChangeBean;
import guopuran.bwie.com.guopuran.bean.ZiBean;

public class ChangeAdapter extends RecyclerView.Adapter<ChangeAdapter.ViewHolder> {
    private List<ZiBean> list=new ArrayList<>();
    private Context context;
    private boolean flag;
    private final int COUNT_LIN=0;
    private final int COUNT_GRID=1;
    public ChangeAdapter(Context context, boolean flag) {
        this.context = context;
        this.flag = flag;
    }

    public List<ZiBean> getList() {
        return list;
    }

    public void setList(List<ZiBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public ZiBean getItem(int position){
        return list.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (flag){
            return COUNT_LIN;
        }else{
            return COUNT_GRID;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(
                (i == COUNT_LIN) ? R.layout.item_lin : R.layout.item_grid
                , viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.getdata(getItem(i),context,i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.change_image);
            title=itemView.findViewById(R.id.change_title);
            price=itemView.findViewById(R.id.change_price);
        }

        public void getdata(final ZiBean item, Context context, final int i) {
           /* String images_url = item.getImages().split("\\|")[0].replace("https", "http");
            Glide.with(context).load(images_url).into(imageView);
            title.setText(item.getTitle());
            price.setText("优惠价:"+item.getPrice());*/
           imageView.setImageResource(item.getCc());
           title.setText(item.getText1());
           price.setText(item.getText2());
           itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   monLongClick.onlongclick(item.getCc());
                   return true;
               }
           });
        }
    }
    public onLongClick monLongClick;
    public void setonLongClick(onLongClick monLongClick){
        this.monLongClick=monLongClick;
    }
    public interface onLongClick{
        void onlongclick(int position);
    }
}
