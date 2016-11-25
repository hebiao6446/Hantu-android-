package hebiao.hantu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by apple on 2016/11/15.
 */

public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.WechatHolder> {


    public interface WechatAdapterDelegate{
        void wechatItemLongClickAction(WechatMessageBean wmb,int postion);
        void wechatItemClickAction(WechatMessageBean wmb,int postion);
        void wechatItemRetryButtonClickAction(WechatMessageBean wmb,int postion);
    }

    private WechatAdapterDelegate delegate;
    public void setDelegate(WechatAdapterDelegate delegate){
        this.delegate=delegate;
    }






    private static final int VIEWTPYE_DEF = 0x9999;
    private static final int VIEWTPYE_HANTU_TEXT = 0x01;
    private static final int VIEWTPYE_HANTU_VOICE = 0x02;
    private static final int VIEWTPYE_HANTU_IMAGE = 0x03;
    private static final int VIEWTPYE_USER_TEXT = 0x04;
    private static final int VIEWTPYE_USER_VOICE = 0x05;
    private static final int VIEWTPYE_USER_IMAGE = 0x06;

    //音频的长度  屏幕比例
    private static final float VOICE_MIN_LENTH=0.2f;
    private static final float VOICE_MAX_LENTH=0.53f;

    //图片的宽 高
    private static final float IMAGE_MAX_HEIGHTORWIDTH = 0.3f;



    private int screenWidth=0;
    private int screenHeight=0;




    private List<WechatMessageBean> list;
    private Context context;

    public WechatAdapter(Context context, List<WechatMessageBean> list) {
        this.list = list;
        this.context = context;
        screenWidth=((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        screenHeight=((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();

    }

    @Override
    public WechatHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        WechatHolder wechatHolder = null;

        switch (viewType) {
            case VIEWTPYE_HANTU_TEXT:
                wechatHolder = new TextHantuChatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wechatcell_text_hantu, parent, false));
                break;
            case VIEWTPYE_HANTU_VOICE:
                wechatHolder = new VoiceHantuChatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wechatcell_voice_hantu, parent, false));
                break;
            case VIEWTPYE_HANTU_IMAGE:
                wechatHolder = new ImageHantuChatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wechatcell_image_hantu, parent, false));
                break;
            case VIEWTPYE_USER_TEXT:
                wechatHolder = new TextUserChatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wechatcell_text_user, parent, false));
                break;
            case VIEWTPYE_USER_VOICE:
                wechatHolder = new VoiceUserChatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wechatcell_voice_user, parent, false));
                break;
            case VIEWTPYE_USER_IMAGE:
                wechatHolder = new ImageUserChatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wechatcell_image_user, parent, false));
                break;
            case VIEWTPYE_DEF:
                wechatHolder = new WechatHolder(parent);
                break;
        }


        return wechatHolder;
    }

    @Override
    public void onBindViewHolder(WechatHolder holder, final int position) {
       final WechatMessageBean wechatMessageBean=list.get(position);
        if (holder instanceof TextHantuChatHolder){
            TextHantuChatHolder newHolder=(TextHantuChatHolder)holder;

             if (wechatMessageBean.isShowMessageTime()){
                 newHolder.cellHantuTextTimeRelativelayout.setVisibility(View.VISIBLE);
             }else {
                 newHolder.cellHantuTextTimeRelativelayout.setVisibility(View.GONE);
             }

            newHolder.cellHantuTextTimeTextview.setText(wechatMessageBean.getMessageTime());
            newHolder.cellHantuTextContextTextview.setText(wechatMessageBean.getMessageText());


            newHolder.cellHantuTextContextRelativelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Log.e("hebiao","======858587788=======setOnClickListener============");
                    if (delegate!=null){
                        delegate.wechatItemClickAction(wechatMessageBean,position);
                    }

                }

            });
            newHolder.cellHantuTextContextRelativelayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

//                    Log.e("hebiao","=====89999========setOnLongClickListener============");

                    if (delegate!=null){
                        delegate.wechatItemLongClickAction(wechatMessageBean,position);
                    }
                    return true;
                }
            });


        }else if (holder instanceof VoiceHantuChatHolder){
          final   VoiceHantuChatHolder newHolder=(VoiceHantuChatHolder)holder;

            if (wechatMessageBean.isShowMessageTime()){
                newHolder.cellHantuVoiceTimeRelativelayout.setVisibility(View.VISIBLE);
            }else {
                newHolder.cellHantuVoiceTimeRelativelayout.setVisibility(View.GONE);
            }




            newHolder.cellHantuVoiceTimeTextview.setText(wechatMessageBean.getMessageTime());
            Integer duringTime=wechatMessageBean.getDuringTime();

            double voiceLength=(VOICE_MAX_LENTH-VOICE_MIN_LENTH)*screenWidth*duringTime/60.0+VOICE_MIN_LENTH*screenWidth;
            ViewGroup.LayoutParams currentParams=newHolder.cellHantuVoiceLevelRelativelayout.getLayoutParams();
            currentParams.width=(int)voiceLength;

            newHolder.cellHantuVoiceLevelRelativelayout.setLayoutParams(currentParams);
            newHolder.cellHantuVoiceDuringtimeTextview.setText(""+duringTime+" ''");

            newHolder.cellHantuVoiceAniotionImageView.setImageResource(R.drawable.play_voice_hantu);
            final  AnimationDrawable animationDrawable = (AnimationDrawable) newHolder.cellHantuVoiceAniotionImageView.getDrawable();
            newHolder.cellHantuVoiceLevelRelativelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (animationDrawable.isRunning()){
                        animationDrawable.stop();
                    }
                    animationDrawable.start();

                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            //execute the task
                            animationDrawable.stop();
                        }
                    }, wechatMessageBean.getDuringTime()*1000);



                    if (delegate!=null){
                        delegate.wechatItemClickAction(wechatMessageBean,position);
                    }


                }
            });
            newHolder.cellHantuVoiceLevelRelativelayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    if (delegate!=null){
                        delegate.wechatItemLongClickAction(wechatMessageBean,position);
                    }

                    return true;
                }
            });


        }else if (holder instanceof ImageHantuChatHolder){
            ImageHantuChatHolder newHolder=(ImageHantuChatHolder)holder;

            if (wechatMessageBean.isShowMessageTime()){
                newHolder.cellHantuImageTimeRelativelayout.setVisibility(View.VISIBLE);
            }else {
                newHolder.cellHantuImageTimeRelativelayout.setVisibility(View.GONE);
            }



            newHolder.cellHantuImageTimeTextview.setText(wechatMessageBean.getMessageTime());
            Bitmap bitmap=wechatMessageBean.getImageBitmap();
            setImageLayout(newHolder.cellHantuImagePictureImageview,wechatMessageBean.getImageUrl());


            newHolder.cellHantuImageRelativelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Log.e("hebiao","========222222===setOnClickListener==============");


                    if (delegate!=null){
                        delegate.wechatItemClickAction(wechatMessageBean,position);
                    }
                }

            });
            newHolder.cellHantuImageRelativelayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

//                    Log.e("hebiao","=======444444=======setOnLongClickListener===========");

                    if (delegate!=null){
                        delegate.wechatItemLongClickAction(wechatMessageBean,position);
                    }

                    return true;
                }
            });

        }else if (holder instanceof TextUserChatHolder){
            TextUserChatHolder newHolder=(TextUserChatHolder)holder;

            if (wechatMessageBean.isShowMessageTime()){
                newHolder.cellUserTextTimeRelativelayout.setVisibility(View.VISIBLE);
            }else {
                newHolder.cellUserTextTimeRelativelayout.setVisibility(View.GONE);
            }





            newHolder.cellUserTextTimeTextview.setText(wechatMessageBean.getMessageTime());
            newHolder.cellUserTextContextTextview.setText(wechatMessageBean.getMessageText());
            if (wechatMessageBean.getSentStatus()== WechatMessageBean.MessageSentStatus.Sended){
                newHolder.cellUserTextResendButton.setVisibility(View.GONE);
                newHolder.cellUserTextLoadingProgressbar.setVisibility(View.GONE);
                newHolder.cellUserTextReadstatusTextview.setVisibility(View.VISIBLE);

                if (wechatMessageBean.getReadStatus()== WechatMessageBean.MessageReadStatus.Read){

                    ColorStateList color = context
                            .getResources()
                            .getColorStateList(R.color.color_bababa);
                    newHolder.cellUserTextReadstatusTextview.setText("已读");
                    newHolder.cellUserTextReadstatusTextview.setTextColor(color);
                }else  if (wechatMessageBean.getReadStatus()== WechatMessageBean.MessageReadStatus.UnRead){
                    newHolder.cellUserTextReadstatusTextview.setText("未读");
                    ColorStateList color = context
                            .getResources()
                            .getColorStateList(R.color.color_c00000);
                    newHolder.cellUserTextReadstatusTextview.setTextColor(color);
                }
            }else if (wechatMessageBean.getSentStatus()== WechatMessageBean.MessageSentStatus.Sending){

                newHolder.cellUserTextResendButton.setVisibility(View.GONE);
                newHolder.cellUserTextLoadingProgressbar.setVisibility(View.VISIBLE);
                newHolder.cellUserTextReadstatusTextview.setVisibility(View.GONE);

            }else if (wechatMessageBean.getSentStatus()== WechatMessageBean.MessageSentStatus.UnSended){
                newHolder.cellUserTextResendButton.setVisibility(View.VISIBLE);
                newHolder.cellUserTextLoadingProgressbar.setVisibility(View.GONE);
                newHolder.cellUserTextReadstatusTextview.setVisibility(View.GONE);
                newHolder.cellUserTextResendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (delegate!=null){
                            delegate.wechatItemRetryButtonClickAction(wechatMessageBean,position);
                        }
                    }
                });
            }



            newHolder.cellUserTextBgviewRelativelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Log.e("hebiao","========99999====cellUserTextBgviewRelativelayout=============");

                    if (delegate!=null){
                        delegate.wechatItemClickAction(wechatMessageBean,position);
                    }
                }
            });
            newHolder.cellUserTextBgviewRelativelayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

//                    Log.e("hebiao","=========000000==========tivelayout.setOnLongClickListener======");

                    if (delegate!=null){
                        delegate.wechatItemLongClickAction(wechatMessageBean,position);
                    }

                    return true;
                }
            });

        }else if (holder instanceof VoiceUserChatHolder){
           final VoiceUserChatHolder newHolder=(VoiceUserChatHolder)holder;


            if (wechatMessageBean.isShowMessageTime()){
                newHolder.cellUserVoiceTimeRelativelayout.setVisibility(View.VISIBLE);
            }else {
                newHolder.cellUserVoiceTimeRelativelayout.setVisibility(View.GONE);
            }

            newHolder.cellUserVoiceTimeTextview.setText(wechatMessageBean.getMessageTime());
            Integer duringTime=wechatMessageBean.getDuringTime();

            double voiceLength=(VOICE_MAX_LENTH-VOICE_MIN_LENTH)*screenWidth*duringTime/60.0+VOICE_MIN_LENTH*screenWidth;

            ViewGroup.LayoutParams currentParams=newHolder.cellUserVoiceLevelviewRelativelayout.getLayoutParams();
            currentParams.width=(int)voiceLength;


            newHolder.cellUserVoiceLevelviewRelativelayout.setLayoutParams(currentParams);
            newHolder.cellUserVoiceDuringtimeTextview.setText(""+duringTime+" ''");










            if (wechatMessageBean.getSentStatus()== WechatMessageBean.MessageSentStatus.Sended){
                newHolder.cellUserVoiceReadstatusTextview.setVisibility(View.VISIBLE);
                newHolder.cellUserVoiceResendButton.setVisibility(View.GONE);
                newHolder.cellUserVoiceLoadingProgressbar.setVisibility(View.GONE);
                if (wechatMessageBean.getReadStatus()== WechatMessageBean.MessageReadStatus.Read){

                    ColorStateList color = context
                            .getResources()
                            .getColorStateList(R.color.color_bababa);
                    newHolder.cellUserVoiceReadstatusTextview.setText("已读");
                    newHolder.cellUserVoiceReadstatusTextview.setTextColor(color);
                }else  if (wechatMessageBean.getReadStatus()== WechatMessageBean.MessageReadStatus.UnRead){
                    newHolder.cellUserVoiceReadstatusTextview.setText("未读");
                    ColorStateList color = context
                            .getResources()
                            .getColorStateList(R.color.color_c00000);
                    newHolder.cellUserVoiceReadstatusTextview.setTextColor(color);
                }
            }else if (wechatMessageBean.getSentStatus()== WechatMessageBean.MessageSentStatus.Sending){
                newHolder.cellUserVoiceReadstatusTextview.setVisibility(View.GONE);
                newHolder.cellUserVoiceResendButton.setVisibility(View.GONE);
                newHolder.cellUserVoiceLoadingProgressbar.setVisibility(View.VISIBLE);
            }else if (wechatMessageBean.getSentStatus()== WechatMessageBean.MessageSentStatus.UnSended){
                newHolder.cellUserVoiceReadstatusTextview.setVisibility(View.GONE);
                newHolder.cellUserVoiceResendButton.setVisibility(View.VISIBLE);
                newHolder.cellUserVoiceLoadingProgressbar.setVisibility(View.GONE);


                newHolder.cellUserVoiceResendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (delegate!=null){
                            delegate.wechatItemRetryButtonClickAction(wechatMessageBean,position);
                        }
                    }
                });
            }


            newHolder.cellUserVoiceAniotionImageView.setImageResource(R.drawable.play_voice_user);
            final  AnimationDrawable animationDrawable = (AnimationDrawable) newHolder.cellUserVoiceAniotionImageView.getDrawable();
            newHolder.cellUserVoiceLevelviewRelativelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (animationDrawable.isRunning()){
                        animationDrawable.stop();
                    }
                    animationDrawable.start();

                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            //execute the task
                            animationDrawable.stop();
                        }
                    }, wechatMessageBean.getDuringTime()*1000);

                    if (delegate!=null){
                        delegate.wechatItemClickAction(wechatMessageBean,position);
                    }


                }
            });
            newHolder.cellUserVoiceLevelviewRelativelayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

//                    Log.e("hebiao","=======6346363======ayout.setOnLongClickListener============");

                    if (delegate!=null){
                        delegate.wechatItemLongClickAction(wechatMessageBean,position);
                    }

                    return true;
                }
            });



        }else if (holder instanceof ImageUserChatHolder){
            ImageUserChatHolder newHolder=(ImageUserChatHolder)holder;


            if (wechatMessageBean.isShowMessageTime()){
                newHolder.cellUserImageTimeRelativelayout.setVisibility(View.VISIBLE);
            }else {
                newHolder.cellUserImageTimeRelativelayout.setVisibility(View.GONE);
            }





            newHolder.cellUserImageTimeTextview.setText(wechatMessageBean.getMessageTime());
            setImageLayout(newHolder.cellUserImagePictureImageview,wechatMessageBean.getImageUrl());





            if (wechatMessageBean.getSentStatus()== WechatMessageBean.MessageSentStatus.Sended){
                newHolder.cellUserImageReadstatusTextview.setVisibility(View.VISIBLE);
                if (wechatMessageBean.getReadStatus()== WechatMessageBean.MessageReadStatus.Read){

                    ColorStateList color = context
                            .getResources()
                            .getColorStateList(R.color.color_bababa);
                    newHolder.cellUserImageReadstatusTextview.setText("已读");
                    newHolder.cellUserImageReadstatusTextview.setTextColor(color);
                }else  if (wechatMessageBean.getReadStatus()== WechatMessageBean.MessageReadStatus.UnRead){
                    newHolder.cellUserImageReadstatusTextview.setText("未读");
                    ColorStateList color = context
                            .getResources()
                            .getColorStateList(R.color.color_c00000);
                    newHolder.cellUserImageReadstatusTextview.setTextColor(color);
                }
            }else if (wechatMessageBean.getSentStatus()== WechatMessageBean.MessageSentStatus.Sending){
                newHolder.cellUserImageLoadingProgressbar.setVisibility(View.VISIBLE);
            }else if (wechatMessageBean.getSentStatus()== WechatMessageBean.MessageSentStatus.UnSended){
                newHolder.cellUserImageResendButton.setVisibility(View.VISIBLE);

                newHolder.cellUserImageResendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (delegate!=null){
                            delegate.wechatItemRetryButtonClickAction(wechatMessageBean,position);
                        }
                    }
                });

            }


            newHolder.cellUserImageRelativelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Log.e("hebiao","======373788885==========setOnClickListener=========");

                    if (delegate!=null){
                        delegate.wechatItemClickAction(wechatMessageBean,position);
                    }
                }
            });
            newHolder.cellUserImageRelativelayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

//                    Log.e("hebiao","=====888858585===========setOnLongClickListener=========");

                    if (delegate!=null){
                        delegate.wechatItemLongClickAction(wechatMessageBean,position);
                    }
                    return true;
                }
            });

        }



    }

    private void setImageLayout(final ImageView img,String url){



        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                ViewGroup.LayoutParams layoutParams=img.getLayoutParams();

                int bw=resource.getWidth();
                int bh=resource.getHeight();

                float maxwh=screenHeight*IMAGE_MAX_HEIGHTORWIDTH;

                Log.e("hebiao =============== ",maxwh+"");
                if (bw>=bh){
                    layoutParams.width=(int)maxwh;
                    layoutParams.height=(int)(bh*maxwh/bw);
                }else {
                    layoutParams.height=(int)maxwh;
                    layoutParams.width=(int)(bw*maxwh/bh);
                }
                img.setLayoutParams(layoutParams);
                img.setImageBitmap(resource);

            }
        });



    }

    private RelativeLayout.LayoutParams getBitmapLayoutParmas(Bitmap b){



       return   new RelativeLayout.LayoutParams( (int)IMAGE_MAX_HEIGHTORWIDTH*screenWidth, (int)IMAGE_MAX_HEIGHTORWIDTH*screenWidth);
/*
        double w=b.getWidth()*1.0;
        double h=b.getHeight()*1.0;

//        横图 宽度为 max
        if (w>=h){
//            return new RelativeLayout.LayoutParams((int)IMAGE_MAX_HEIGHTORWIDTH*screenWidth,(int)(h*IMAGE_MAX_HEIGHTORWIDTH/w));
        }else {
//            return new RelativeLayout.LayoutParams( (int)(w*IMAGE_MAX_HEIGHTORWIDTH/h),IMAGE_MAX_HEIGHTORWIDTH);
        }

*/





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        WechatMessageBean wechatMessageBean = list.get(position);
        if (wechatMessageBean.getSenderType() == WechatMessageBean.MessageSenderType.Hantu) {
            if (wechatMessageBean.getMessageType() == WechatMessageBean.MessageType.Text) {
                return VIEWTPYE_HANTU_TEXT;
            } else if (wechatMessageBean.getMessageType() == WechatMessageBean.MessageType.Voice) {
                return VIEWTPYE_HANTU_VOICE;
            } else if (wechatMessageBean.getMessageType() == WechatMessageBean.MessageType.Image) {
                return VIEWTPYE_HANTU_IMAGE;
            }

        } else if (wechatMessageBean.getSenderType() == WechatMessageBean.MessageSenderType.User) {
            if (wechatMessageBean.getMessageType() == WechatMessageBean.MessageType.Text) {
                return VIEWTPYE_USER_TEXT;
            } else if (wechatMessageBean.getMessageType() == WechatMessageBean.MessageType.Voice) {
                return VIEWTPYE_USER_VOICE;
            } else if (wechatMessageBean.getMessageType() == WechatMessageBean.MessageType.Image) {
                return VIEWTPYE_USER_IMAGE;
            }
        }


        return VIEWTPYE_DEF;
    }

    class WechatHolder extends RecyclerView.ViewHolder {

        public WechatHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    final class TextHantuChatHolder extends WechatHolder {

        @BindView(R.id.cell_hantu_text_context_relativelayout)
        RelativeLayout cellHantuTextContextRelativelayout;

        @BindView(R.id.cell_hantu_text_time_textview)
        TextView cellHantuTextTimeTextview;
        @BindView(R.id.cell_hantu_text_time_relativelayout)
        RelativeLayout cellHantuTextTimeRelativelayout;
        @BindView(R.id.cell_hantu_text_logo_imageview)
        ImageView cellHantuTextLogoImageview;
        @BindView(R.id.cell_hantu_text_context_textview)
        TextView cellHantuTextContextTextview;

        public TextHantuChatHolder(View itemView) {
            super(itemView);

        }
    }


    final class VoiceHantuChatHolder extends WechatHolder {
        @BindView(R.id.cell_hantu_voice_time_textview)
        TextView cellHantuVoiceTimeTextview;
        @BindView(R.id.cell_hantu_voice_time_relativelayout)
        RelativeLayout cellHantuVoiceTimeRelativelayout;
        @BindView(R.id.cell_hantu_voice_logo_imageview)
        ImageView cellHantuVoiceLogoImageview;
        @BindView(R.id.cell_hantu_voice_level_relativelayout)
        RelativeLayout cellHantuVoiceLevelRelativelayout;
        @BindView(R.id.cell_hantu_voice_aniotion_imageview)
        ImageView cellHantuVoiceAniotionImageView;
        @BindView(R.id.cell_hantu_voice_duringtime_textview)
        TextView cellHantuVoiceDuringtimeTextview;

        public VoiceHantuChatHolder(View itemView) {
            super(itemView);
        }
    }

    final class ImageHantuChatHolder extends WechatHolder {


        @BindView(R.id.cell_hantu_image_relativelayout)
        RelativeLayout cellHantuImageRelativelayout;

        @BindView(R.id.cell_hantu_image_time_textview)
        TextView cellHantuImageTimeTextview;
        @BindView(R.id.cell_hantu_image_time_relativelayout)
        RelativeLayout cellHantuImageTimeRelativelayout;
        @BindView(R.id.cell_hantu_image_logo_imageview)
        ImageView cellHantuImageLogoImageview;
        @BindView(R.id.cell_hantu_image_picture_imageview)
        BubbleImageView cellHantuImagePictureImageview;

        public ImageHantuChatHolder(View itemView) {
            super(itemView);
        }
    }

    final class TextUserChatHolder extends WechatHolder {

        @BindView(R.id.cell_user_text_time_textview)
        TextView cellUserTextTimeTextview;
        @BindView(R.id.cell_user_text_time_relativelayout)
        RelativeLayout cellUserTextTimeRelativelayout;
        @BindView(R.id.cell_user_text_logo_imageview)
        ImageView cellUserTextLogoImageview;
        @BindView(R.id.cell_user_text_context_textview)
        TextView cellUserTextContextTextview;
        @BindView(R.id.cell_user_text_bgview_relativelayout)
        RelativeLayout cellUserTextBgviewRelativelayout;
        @BindView(R.id.cell_user_text_resend_button)
        Button cellUserTextResendButton;
        @BindView(R.id.cell_user_text_loading_progressbar)
        ProgressBar cellUserTextLoadingProgressbar;
        @BindView(R.id.cell_user_text_readstatus_textview)
        TextView cellUserTextReadstatusTextview;

        public TextUserChatHolder(View itemView) {
            super(itemView);
        }
    }

    final class VoiceUserChatHolder extends WechatHolder {
        @BindView(R.id.cell_user_voice_time_textview)
        TextView cellUserVoiceTimeTextview;
        @BindView(R.id.cell_user_voice_time_relativelayout)
        RelativeLayout cellUserVoiceTimeRelativelayout;
        @BindView(R.id.cell_user_voice_logo_imageview)
        ImageView cellUserVoiceLogoImageview;
        @BindView(R.id.cell_user_voice_level_relativelayout)
        RelativeLayout cellUserVoiceLevelviewRelativelayout;

        @BindView(R.id.cell_user_voice_aniotion_imageview)
        ImageView cellUserVoiceAniotionImageView;

        @BindView(R.id.cell_user_voice_duringtime_textview)
        TextView cellUserVoiceDuringtimeTextview;
        @BindView(R.id.cell_user_voice_resend_button)
        Button cellUserVoiceResendButton;
        @BindView(R.id.cell_user_voice_loading_progressbar)
        ProgressBar cellUserVoiceLoadingProgressbar;
        @BindView(R.id.cell_user_voice_readstatus_textview)
        TextView cellUserVoiceReadstatusTextview;

        public VoiceUserChatHolder(View itemView) {
            super(itemView);
        }
    }


    final class ImageUserChatHolder extends WechatHolder {

        @BindView(R.id.cell_user_image_relativelayout)
        RelativeLayout cellUserImageRelativelayout;

        @BindView(R.id.cell_user_image_time_textview)
        TextView cellUserImageTimeTextview;
        @BindView(R.id.cell_user_image_time_relativelayout)
        RelativeLayout cellUserImageTimeRelativelayout;
        @BindView(R.id.cell_user_image_logo_imageview)
        ImageView cellUserImageLogoImageview;
        @BindView(R.id.cell_user_image_picture_imageview)
        BubbleImageView cellUserImagePictureImageview;
        @BindView(R.id.cell_user_image_resend_button)
        Button cellUserImageResendButton;
        @BindView(R.id.cell_user_image_loading_progressbar)
        ProgressBar cellUserImageLoadingProgressbar;
        @BindView(R.id.cell_user_image_readstatus_textview)
        TextView cellUserImageReadstatusTextview;
        public ImageUserChatHolder(View itemView) {
            super(itemView);
        }
    }

}
