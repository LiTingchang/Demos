// Generated code from Butter Knife. Do not modify!
package com.jitizhihui.wxassistant;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChargeActivity$$ViewInjector {
  public static void inject(Finder finder, final com.jitizhihui.wxassistant.ChargeActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296256, "field 'title'");
    target.title = (com.ltc.lib.commontitle.CommonTitle) view;
    view = finder.findRequiredView(source, 2131296260, "field 'chargeCodeEditText'");
    target.chargeCodeEditText = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296259, "field 'chargePhoneNUmberEditText'");
    target.chargePhoneNUmberEditText = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296261, "field 'chargeButton'");
    target.chargeButton = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131296258, "field 'availableTextView'");
    target.availableTextView = (android.widget.TextView) view;
  }

  public static void reset(com.jitizhihui.wxassistant.ChargeActivity target) {
    target.title = null;
    target.chargeCodeEditText = null;
    target.chargePhoneNUmberEditText = null;
    target.chargeButton = null;
    target.availableTextView = null;
  }
}
