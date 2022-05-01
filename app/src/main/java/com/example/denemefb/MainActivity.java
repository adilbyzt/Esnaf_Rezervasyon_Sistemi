package com.example.denemefb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public int acilisSaati, kapanisSaati, calismaAralikDk;
    private DatePickerDialog tarihSeciciDialog;// kullanıcının randevu tarihi seçtiği kısımdaki tarih ayarlarını yapmamız için gerekli bileşenler
    private Button tarihButon;
    public String secilentarihDBFormat;//bu String değişkende veritabanına kaydedeceğimiz formattaki son tarih değeri bulunur örn: "26_04_2022"
    //public String docPath;
    //public String curDay;

    RecyclerView mRecyclerView;                //arraydeki her veriyi listelememiz için RecyclerView itemını kullandık
    RecyclerView.Adapter timeSlotAdapter, timeSlotControlAdapter;            //arraydaki her veriyi tek tek listelemek için adapter kullanmamız gerek

    FirebaseFirestore db;
    ArrayList firebaseData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acilisSaati = 9;  // default açılış saati
        kapanisSaati = 17;  //default kapanış saati
        calismaAralikDk = 30;  //default çalışma aralığı dk

        initDatePicker();
        db = FirebaseFirestore.getInstance();
        firebaseData = new ArrayList<>();

        tarihButon = findViewById(R.id.tarihsecici);   //tarih seçeceğimiz butonu tanımladık
        tarihButon.setText(bugununTarihi());          //bugünün tarihini default olarak ayarladık

        //docPath=bugununTarihi();

        Log.d("siraTest", "tst1");

        Log.d("buguntarih", secilentarihDBFormat);
        //Log.d();


        getBerberCalismaSaatleri("5uHEzPp8nZNmmrxL3cUm");
    }

    public void getBerberCalismaSaatleri(String docPath) { //veritabanına kayıtlı olan esnafın çalışma saatlerini ve randevu aralığı bilgilerini alır
        DocumentReference docRef = db.collection("esnaflar").document("5uHEzPp8nZNmmrxL3cUm");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("TAG", "DocumentSnapshot data: " + document.get("dukkanacilissaat"));

                        acilisSaati = Integer.parseInt(document.get("dukkanacilissaat").toString());
                        Log.d("TAG", "acilisSaat:" + acilisSaati);
                        kapanisSaati = Integer.parseInt(document.get("dukkankapanissaati").toString());
                        Log.d("TAG", "acilisSaat:" + kapanisSaati);
                        calismaAralikDk = Integer.parseInt(document.get("randevuaraligidakika").toString());
                        Log.d("TAG", "acilisSaat:" + calismaAralikDk);
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
                fetchSlotControlData("5uHEzPp8nZNmmrxL3cUm", secilentarihDBFormat);   //[3,4,5,6] geldiğinde 3 4 5 ve 6. slotlar dolu demektir
            }
        });
    }

    public void fetchSlotControlData(String docPath, String curDay) { //berberin randevu aralıklarının dolu ve boş olmasını ayarlar
        Log.d("test5", curDay);
        db.collection("esnaflar").document(docPath).collection(curDay)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                firebaseData.add(document.get("slot"));
                                Log.d("denemedeneme123", document.getId() + " => " + document.get("slot"));
                            }
                            mRecyclerView = findViewById(R.id.recycler_view);  //recyclerView itemını tanımladık
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3); //listeleyeciğimiz array elemanlarının düzenini ayarladık
                            timeSlotAdapter = new TimeSlotAdapter(getTimeSet(acilisSaati, kapanisSaati, calismaAralikDk), firebaseData); //TimeSlotAdapter isimli adaptor e zaman aralıklarının olduğu array listemizi gönderdik
                            mRecyclerView.setLayoutManager(gridLayoutManager);  //recylerView'in layaut tipini yukarda oluşturduğumuz GridLayoutManager olarak ayarladık
                            mRecyclerView.setAdapter(timeSlotAdapter);   //RecyclerView in adapter'inin mAdapter isimli adapter olduğunu söyledik
                            //getTimeSet(8,17,30); //esnafın seçtiği randevu aralığı ayarı Esnaf Acilis Saati:8, Esnaf Kapanis Saati:17, Esnaf Randevu Aralığı:30dk


                        } else {
                            Log.d("denemedeneme123", "Error getting documents: ", task.getException());
                        }

                        Log.d("siraTest", "tst3");
                        Log.d("denemeArray", firebaseData.toString());
                    }
                });
    }


    private String bugununTarihi() {
        Calendar cal = Calendar.getInstance();
        int yil = cal.get(Calendar.YEAR); //güncel yil
        int ay = cal.get(Calendar.MONTH); //güncel ay
        ay = ay + 1;
        int gun = cal.get(Calendar.DAY_OF_MONTH); //güncel gün
        return makeDateString(gun, ay, yil);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yil, int ay, int gun) {  // tarih her değiştiğinde içerisine girer
                ay = ay + 1;
                String tarih = makeDateString(gun, ay, yil); //makeDateString metoduna datePicker'in yil, ay, gün değerlerini gönder
                tarihButon.setText(tarih); //tarih değerini ekrana yazdırıyoruz
                Log.d("tarihtest", "degisti" + secilentarihDBFormat);

                //Toast.makeText(MainActivity.this, "degisti"+secilentarihDBFormat, Toast.LENGTH_SHORT).show();
                firebaseData.clear(); //tabloyu sıfırla sorguyu tekrar gönder
                fetchSlotControlData("5uHEzPp8nZNmmrxL3cUm", secilentarihDBFormat); //tarih değiştiği yeni tarih ile sorguyu yeniden gönderiyorum
                Log.d("test4", secilentarihDBFormat);
            }
        };
        Calendar cal = Calendar.getInstance();
        int yil = cal.get(Calendar.YEAR); // güncel yıl
        int ay = cal.get(Calendar.MONTH); // güncel ay
        int gun = cal.get(Calendar.DAY_OF_MONTH); //güncel gün

        int style = AlertDialog.THEME_HOLO_LIGHT; //style ayarı

        tarihSeciciDialog = new DatePickerDialog(this, style, dateSetListener, yil, ay, gun); //tarih seçici diyalog oluştur
        tarihSeciciDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); //güncel tarihten önceki tarihleri seçimi yasakla
    }

    private String makeDateString(int gun, int ay, int yil) {
        secilentarihDBFormat = gun + "_" + ay + "_" + yil;
        Log.d("denemedeneme", secilentarihDBFormat);
        return gun + " " + ayFormati(ay) + " " + yil;  // return edeceği formatı ayarladık yani şu şekilde -> "7 OCAK 2022"
    }

    public ArrayList<String> getTimeSet(int baslamaSaati, int bitisSaati, int aralikDakika) {
        if (baslamaSaati >= bitisSaati) {
            bitisSaati = bitisSaati + 24;
        }


        ArrayList results = new ArrayList<String>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, baslamaSaati); //başlangıç saati 9
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        int donguSayisi = ((bitisSaati - baslamaSaati) * 60) / aralikDakika;
        for (int i = 0; i < donguSayisi; i++) { //9 dan başlayarak kaç seans olacağı 15 seans
            String frmt = DateFormat.getTimeInstance().format(calendar.getTime());
            String[] saatiBol = frmt.split(":");
            String saat1 = saatiBol[0] + ":" + saatiBol[1];
            calendar.add(Calendar.MINUTE, aralikDakika); //30dk 30dk arttır
            String frmt2 = DateFormat.getTimeInstance().format(calendar.getTime());
            String[] saatiBol2 = frmt2.split(":");
            String saat2 = saatiBol2[0] + ":" + saatiBol2[1];

            String sonuc = saat1 + "-" + saat2;
            results.add(i, sonuc);
        }
        Log.d("testtest", results.toString());
        return results;
    }

    private String ayFormati(int ay) {  // SEÇİLEN AY DEĞERİNİN HANGİ AY ADINA DENK GELDİĞİNİ BELİRTTİK
        switch (ay) {
            case 1:
                return "OCAK";
            case 2:
                return "ŞUBAT";
            case 3:
                return "MART";
            case 4:
                return "NİSAN";
            case 5:
                return "MAYIS";
            case 6:
                return "HAZİRAN";
            case 7:
                return "TEMMUZ";
            case 8:
                return "AĞUSTOS";
            case 9:
                return "EYLÜL";
            case 10:
                return "EKİM";
            case 11:
                return "KASIM";
            case 12:
                return "ARALIK";
            default:
                return "OCAK";
        }
    }

    public void acikTarihSecici(View view) {
        tarihSeciciDialog.show();
    }


}