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
import guopuran.bwie.com.guopuran.bean.JiuBean;

public class JiuAdapter extends RecyclerView.Adapter<JiuAdapter.ViewHolder> {
    private List<JiuBean.DataBean> list;
    private Context context;

    public JiuAdapter(Context context) {
        this.context = context;
        //初始化
        list=new ArrayList<>();
    }

    public JiuBean.DataBean getItem(int position){
        return list.get(position);
    }

    public void setList(List<JiuBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jiu, viewGroup, false);
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
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.jiu_image);
            name=itemView.findViewById(R.id.jiu_name);
        }

        public void getdata(JiuBean.DataBean item, Context context, int i) {
            Glide.with(context).load(item.getIcon()).into(imageView);
            name.setText(item.getName());
        }
    }
}
