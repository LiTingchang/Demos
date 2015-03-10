// Generated code from Butter Knife. Do not modify!
package com.jitizhihui.wxassistant;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AboutActivity$$ViewInjector {
  public static void inject(Finder finder, final com.jitizhihui.wxassistant.AboutActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296256, "field 'title'");
    target.title = (com.ltc.lib.commontitle.CommonTitle) view;
  }

  public static void reset(com.jitizhihui.wxassistant.AboutActivity target) {
    target.title = null;
  }
}
