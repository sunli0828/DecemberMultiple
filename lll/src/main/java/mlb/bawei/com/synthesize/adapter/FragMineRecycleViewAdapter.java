package mlb.bawei.com.synthesize.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import mlb.bawei.com.synthesize.R;
import mlb.bawei.com.synthesize.bean.MineDataBean;

/**
 * @author
 * @date 2018/12/30
 */
public class FragMineRecycleViewAdapter extends RecyclerView.Adapter<FragMineRecycleViewAdapter.ViewHolderMine>{
    private Context context;
    private List<MineDataBean> list = new ArrayList<>();

    public FragMineRecycleViewAdapter(Context context,List<MineDataBean> list) {
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolderMine onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.mine_adapter_item_recycle, null);
        return new ViewHolderMine(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMine viewHolderMine, int i) {
        viewHolderMine.imageView.setImageResource(list.get(i).getInteger());
        viewHolderMine.textView.setText(list.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolderMine extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolderMine(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.mine_adapter_item_name);
            imageView = itemView.findViewById(R.id.mine_adapter_item_image);
        }
    }
}
