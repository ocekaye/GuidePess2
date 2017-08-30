package okki.setyatmoko.guidepess.Models;

import java.util.List;

/**
 * Created by Hinata on 8/12/2017.
 */
public class HomeStyle extends BaseModelContent{
    private String style = "";
    private ButonData butonData;
    private List<SlideData> slideData;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public ButonData getButonData() {
        return butonData;
    }

    public void setButonData(ButonData butonData) {
        this.butonData = butonData;
    }

    public List<SlideData> getSlideData() {
        return slideData;
    }

    public void setSlideData(List<SlideData> slideData) {
        this.slideData = slideData;
    }

    public class ButonData {
        private ContenData content;
        private List<HomeButtonData> buttons;

        public ContenData getContent() {
            return content;
        }

        public void setContent(ContenData content) {
            this.content = content;
        }

        public List<HomeButtonData> getButtons() {
            return buttons;
        }

        public void setButtons(List<HomeButtonData> buttons) {
            this.buttons = buttons;
        }
    }

    public class SlideData {
        private String title = "";
        private ContenData content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ContenData getContent() {
            return content;
        }

        public void setContent(ContenData content) {
            this.content = content;
        }
    }

    public class HomeButtonData {
        private String text = "";
        private String icon = "";
        private String link = "";

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
