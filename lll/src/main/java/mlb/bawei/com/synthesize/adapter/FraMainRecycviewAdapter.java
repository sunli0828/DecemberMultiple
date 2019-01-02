package mlb.bawei.com.synthesize.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mlb.bawei.com.synthesize.R;

/**
 * @author
 * @date 2018/12/29
 */
public class FraMainRecycviewAdapter extends RecyclerView.Adapter<FraMainRecycviewAdapter.ViewHolderMain>{
    private Context context;
    private List<String> list = new ArrayList<>();

    public FraMainRecycviewAdapter(Context context,List<String> list) {
        this.context = context;
        this.list=list;
    }


    @NonNull
    @Override
    public ViewHolderMain onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.main_adapter_top_item, null);
        return new ViewHolderMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMain viewHolderMain, int i) {
        viewHolderMain.textView.setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderMain extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolderMain(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.main_adapter_topitem_text);
        }
    }
}
