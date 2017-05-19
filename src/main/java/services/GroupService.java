package services;


import com.jfoenix.controls.JFXListView;
import javafx.scene.control.ListView;

/**
 * Created by frede on 19-05-2017.
 */
public class GroupService {

    public void setListView(JFXListView<String> listView) {

        listView.getItems().addAll("Frederik", "Fredrik", "Jason", "Jakob");
    }
}
