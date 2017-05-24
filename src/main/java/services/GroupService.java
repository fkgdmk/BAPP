package services;


import com.jfoenix.controls.JFXListView;
import javafx.scene.control.ListView;
import models.Member;
import java.util.List;

/**
 * Created by frede on 19-05-2017.
 */
public class GroupService
{

    public void setListView(JFXListView<String> listView, String groupId) {

        List <Member> members = Member.where("group_id = " + groupId);
        for (Member member: members) {
            listView.getItems().add(member.get("name").toString());
        }
    }
}
