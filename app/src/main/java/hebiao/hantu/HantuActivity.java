package hebiao.hantu;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HantuActivity extends AppCompatActivity implements WechatAdapter.WechatAdapterDelegate{


    List<WechatMessageBean> list = null;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    WechatAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechatactivity);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        allocList();


        adapter=new WechatAdapter(this,list);
        adapter.setDelegate(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);



    }


    public void buttonClick(View view) {

//        startActivity(new Intent(this,WechatActivity.class));

    }


    private void allocList() {
        WechatMessageBean wmb=null;

        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Text);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.Hantu);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sended);
        list.add(wmb);




        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
        wmb.setShowMessageTime(true);
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Text);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.User);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sended);
        wmb.setReadStatus(WechatMessageBean.MessageReadStatus.Read);
        list.add(wmb);


        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
        wmb.setShowMessageTime(true);
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Voice);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.Hantu);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sended);
        wmb.setReadStatus(WechatMessageBean.MessageReadStatus.Read);
        list.add(wmb);


        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Text);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.User);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sended);
        wmb.setReadStatus(WechatMessageBean.MessageReadStatus.UnRead);
        list.add(wmb);


        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Text);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.User);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sending);
        list.add(wmb);


        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Text);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.User);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.UnSended);
        list.add(wmb);



        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
        wmb.setShowMessageTime(true);
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Voice);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.User);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sending);
        list.add(wmb);


        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
        wmb.setShowMessageTime(true);
        wmb.setImageUrl("http://7fvipe.com1.z0.glb.clouddn.com/test8.png");
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Image);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.Hantu);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sending);
        list.add(wmb);


        wmb=new WechatMessageBean();
        wmb.setDuringTime(30);
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Voice);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.User);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.UnSended);
        list.add(wmb);


        wmb=new WechatMessageBean();
        wmb.setDuringTime(50);
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Voice);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.User);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sended);
        wmb.setReadStatus(WechatMessageBean.MessageReadStatus.Read);
        list.add(wmb);


        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
        wmb.setShowMessageTime(true);
//        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setMessageType(WechatMessageBean.MessageType.Voice);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.User);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sended);
        wmb.setReadStatus(WechatMessageBean.MessageReadStatus.Read);
        list.add(wmb);

        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setShowMessageTime(true);
        wmb.setMessageType(WechatMessageBean.MessageType.Image);
        wmb.setImageUrl("http://7fvipe.com1.z0.glb.clouddn.com/test1.png");
        wmb.setSenderType(WechatMessageBean.MessageSenderType.Hantu);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sended);
        wmb.setReadStatus(WechatMessageBean.MessageReadStatus.Read);
        list.add(wmb);


        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setImageUrl("http://7fvipe.com1.z0.glb.clouddn.com/test2.png");
        wmb.setMessageType(WechatMessageBean.MessageType.Image);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.User);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sended);
        wmb.setReadStatus(WechatMessageBean.MessageReadStatus.Read);
        list.add(wmb);


        wmb=new WechatMessageBean();
        wmb.setDuringTime(10);
        wmb.setShowMessageTime(true);
        wmb.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.wechatimg));
        wmb.setMessageText("据了解，上海市人大常委会近日表决通过了新修订的《上海市公共场所控制吸烟条例》。明年3月1日起，上海公共场所控烟范围将扩大，室内公共场所、室内工作场所、公共交通工具内禁止吸烟。这一措施得到世界卫生组织总干事陈冯富珍的赞赏。");
        wmb.setMessageTime("11:22");
        wmb.setImageUrl("http://7fvipe.com1.z0.glb.clouddn.com/test4.png");
        wmb.setMessageType(WechatMessageBean.MessageType.Image);
        wmb.setSenderType(WechatMessageBean.MessageSenderType.User);
        wmb.setSentStatus(WechatMessageBean.MessageSentStatus.Sended);
        wmb.setReadStatus(WechatMessageBean.MessageReadStatus.Read);
        list.add(wmb);
    }


    @Override
    public void wechatItemLongClickAction(WechatMessageBean wmb, int postion) {
        Toast.makeText(this, "wechatItemLongClickAction", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void wechatItemClickAction(WechatMessageBean wmb, int postion) {
        Toast.makeText(this, "wechatItemClickAction", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void wechatItemRetryButtonClickAction(WechatMessageBean wmb, int postion) {
        Toast.makeText(this, "wechatItemRetryButtonClickAction", Toast.LENGTH_SHORT).show();

    }
}
