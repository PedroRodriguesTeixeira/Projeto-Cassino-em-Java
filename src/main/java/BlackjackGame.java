import Control.BlackjackController;
import Model.BlackjackModel;
import View.BlackjackView;
import javax.swing.*;

public class BlackjackGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BlackjackModel model = new BlackjackModel();
            BlackjackView view = new BlackjackView(null);
            BlackjackController controller = new BlackjackController(model, view);
            view.createGUI(controller); 
        });
    }
}