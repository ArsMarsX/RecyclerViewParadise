package com.revolve44.horizontalrecyclerview8;

import android.content.Context;
import android.media.MediaPlayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model> dataSet;
    Context mContext;
    int total_types;
    MediaPlayer mPlayer;
    private boolean fabStateVolume = false;

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        CardView cardView;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.image = (ImageView) itemView.findViewById(R.id.background);

        }

    }

    public static class AudioTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        FloatingActionButton fab;

        public AudioTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            //this.fab = (FloatingActionButton) itemView.findViewById(R.id.fab);

        }

    }

    public static class GraphTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        ValueLineChart mCubicValueLineChart;
        //CardView cardView2;

        public GraphTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            //this.cardView2 = (CardView) itemView.findViewById(R.id.card_view);
            mCubicValueLineChart = (ValueLineChart) itemView.findViewById(R.id.cubiclinechart);

            HashMap<String, Integer> hash = new HashMap<>();
            hash.put("cat", 1);
            hash.put("dog", 2);
            hash.put("bird", 3);

            Set<String> keys = hash.keySet();
            Collection<Integer> value = hash.values();

            ValueLineSeries series = new ValueLineSeries();
            series.setColor(0xFF56B7F1);


            //series.addPoint(new ValueLinePoint("lol", 2.4f));

            series.addPoint(new ValueLinePoint("0",   0f));

            for (String key : hash.keySet()) {
                series.addPoint(new ValueLinePoint(key,   (float)hash.get(key)));
            }
            series.addPoint(new ValueLinePoint("0",   0f));

//            series.addPoint(new ValueLinePoint("Feb", 3.4f));
//            series.addPoint(new ValueLinePoint("Mar", .4f));
//            series.addPoint(new ValueLinePoint("Apr", 1.2f));
//            series.addPoint(new ValueLinePoint("Mai", 2.6f));
//            series.addPoint(new ValueLinePoint("Jun", 1.0f));
//            series.addPoint(new ValueLinePoint("Jul", 3.5f));
//            series.addPoint(new ValueLinePoint("Aug", 2.4f));
//            series.addPoint(new ValueLinePoint("Sep", 2.4f));
//            series.addPoint(new ValueLinePoint("Oct", 3.4f));
//            series.addPoint(new ValueLinePoint("Nov", .4f));
//            series.addPoint(new ValueLinePoint("Dec", 1.3f));

            mCubicValueLineChart.addSeries(series);
            mCubicValueLineChart.startAnimation();
        }
    }

    public MultiViewTypeAdapter(ArrayList<Model> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type, parent, false);
                return new TextTypeViewHolder(view);
            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_type, parent, false);
                return new ImageTypeViewHolder(view);
            case Model.AUDIO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_type, parent, false);
                return new AudioTypeViewHolder(view);
            case Model.GRAPH_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_type, parent, false);
                return new GraphTypeViewHolder(view);// here has been invisible error
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Model.TEXT_TYPE;
            case 1:
                return Model.IMAGE_TYPE;
            case 2:
                return Model.AUDIO_TYPE;
            case 3:
                return Model.GRAPH_TYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        Model object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case Model.TEXT_TYPE:
                    ((TextTypeViewHolder) holder).txtType.setText(object.text);

                    break;
                case Model.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);
                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
                    break;
                case Model.AUDIO_TYPE:

                    ((AudioTypeViewHolder) holder).txtType.setText(object.text);


                    ((AudioTypeViewHolder) holder).fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


//                            if (fabStateVolume) {
//                                if (mPlayer.isPlaying()) {
//                                    mPlayer.stop();
//
//                                }
//                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.volume);
//                                fabStateVolume = false;
//
//                            } else {
//                                mPlayer = MediaPlayer.create(mContext, R.raw.sound);
//                                mPlayer.setLooping(true);
//                                mPlayer.start();
//                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.drawable.mute);
//                                fabStateVolume = true;
//
//                            }
                        }
                    });


                    break;
                case Model.GRAPH_TYPE:
                    ((GraphTypeViewHolder) holder).txtType.setText(object.text);
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
