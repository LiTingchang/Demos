// Generated code from Butter Knife. Do not modify!
package com.jitizhihui.wxassistant;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class TipsDialogFragment$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.jitizhihui.wxassistant.TipsDialogFragment.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296283, "field 'title'");
    target.title = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296284, "field 'content'");
    target.content = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296282, "field 'li'");
    target.li = (android.widget.ImageView) view;
  }

  public static void reset(com.jitizhihui.wxassistant.TipsDialogFragment.ViewHolder target) {
    target.title = null;
    target.content = null;
    target.li = null;
  }
}
