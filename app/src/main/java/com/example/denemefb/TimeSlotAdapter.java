package com.example.denemefb;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.ViewHolder> {

    ArrayList<String> getTimeSlot;  // saat değerlerimizin olduğu array 9-10 10-11 11-12 şeklinde
    ArrayList<Integer> timeSlotList;
    ArrayList slotControlList;


    public TimeSlotAdapter(ArrayList<String> contacts,ArrayList<String> SlotControl) {
        getTimeSlot = contacts; //contacts arrayini getTimeSlot arrayine attık
        slotControlList=SlotControl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false); //row.xml layout' unu view olarak aldık


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.secilenSlot=position;
        Log.d("test1",slotControlList.toString());
        holder.rezervasyonSaat.setText(getTimeSlot.get(position));
        Log.d("deneme11",getTimeSlot.toString());
        holder.rezervasyonDurum.setText("BOS");//başlangıçta durumları boş olarak ayarla
        holder.rezervasyonSaat.setTextColor(Color.parseColor("#FF000000"));  //default renk saat yazısını siyah yap
        holder.rezervasyonDurum.setTextColor(Color.parseColor("#FF4CAF50")); //default renk durum yazısını yeşil yap

        if (slotControlList.size() == 0) { //liste boşsa hepsini boş yap
            holder.rezervasyonDurum.setText("BOS");
            holder.rezervasyonSaat.setTextColor(Color.parseColor("#FF000000"));  //boş slotların saat rengini siyah yap
            holder.rezervasyonDurum.setTextColor(Color.parseColor("#FF4CAF50")); //boş slotların durum rengini yeşil yap
        } else {  //liste boş değilse
            for (int i = 0; i < slotControlList.size(); i++) { //listedeki eleman sayısı kadar dön
                Log.d("deneme123",slotControlList.get(i)+ " " + position);

                if (Integer.parseInt(slotControlList.get(i).toString()) == position) { //eğer listedeki elemanın slotu ile position eşitse değerini DOLU olarak değiştir
                    holder.rezervasyonDurum.setText("DOLU");
                    Log.d("deneme111", position + "-" + slotControlList.get(i));
                    holder.rezervasyonSaat.setTextColor(Color.parseColor("#FFF13C2F")); //dolu slotların rengini kırmızı yap
                    holder.rezervasyonDurum.setTextColor(Color.parseColor("#FFF13C2F")); //dolu slotların rengini kırmızı yap
                    holder.itemView.setClickable(false);
                    //holder.itemView.setClickable();


                    //listView.setClickable(true);
                    //adapter.isEnabled(0);
                    //listView.getChildAt(1).setEnabled(false);
                }

            }
        }

    }

    @Override
    public int getItemCount() {
        return getTimeSlot.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView rezervasyonSaat, rezervasyonDurum; //row.xml içerisindeki textViewları burada tanımladık
        public View view;
        public int secilenSlot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("click", String.valueOf(secilenSlot));
                }
            });
            rezervasyonSaat = (TextView) itemView.findViewById(R.id.rezervasyonSaat); //row.xml içindeki ilk textView
            rezervasyonDurum = (TextView) itemView.findViewById(R.id.rezervasyonDurum); //row.xml içindeki ikinci textView


        }
    }


}