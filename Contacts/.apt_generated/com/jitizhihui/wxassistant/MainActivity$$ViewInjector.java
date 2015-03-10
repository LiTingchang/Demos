// Generated code from Butter Knife. Do not modify!
package com.jitizhihui.wxassistant;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainActivity$$ViewInjector {
  public static void inject(Finder finder, final com.jitizhihui.wxassistant.MainActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296263, "field 'editText'");
    target.editText = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296262, "field 'msgTextView'");
    target.msgTextView = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296268, "field 'addButton' and method 'addPhoneNumber'");
    target.addButton = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.addPhoneNumber();
        }
      });
    view = finder.findRequiredView(source, 2131296269, "field 'deleteButton' and method 'deletePhoneNumber'");
    target.deleteButton = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.deletePhoneNumber();
        }
      });
    view = finder.findRequiredView(source, 2131296272, "field 'tipsButton' and method 'showTips'");
    target.tipsButton = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.showTips();
        }
      });
    view = finder.findRequiredView(source, 2131296270, "field 'stopButton' and method 'stop'");
    target.stopButton = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.stop();
        }
      });
    view = finder.findRequiredView(source, 2131296267, "field 'luckButton' and method 'getLuckPhoneNumber'");
    target.luckButton = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.getLuckPhoneNumber();
        }
      });
    view = finder.findRequiredView(source, 2131296271, "field 'getActivateButton' and method 'launchChargeActivity'");
    target.getActivateButton = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.launchChargeActivity();
        }
      });
    view = finder.findRequiredView(source, 2131296256, "field 'title'");
    target.title = (com.ltc.lib.commontitle.CommonTitle) view;
    view = finder.findRequiredView(source, 2131296265, "method 'addHundredCheched'");
    ((android.widget.CompoundButton) view).setOnCheckedChangeListener(
      new android.widget.CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(
          android.widget.CompoundButton p0,
          boolean p1
        ) {
          target.addHundredCheched(p1);
        }
      });
    view = finder.findRequiredView(source, 2131296264, "method 'onAddTenChecked'");
    ((android.widget.CompoundButton) view).setOnCheckedChangeListener(
      new android.widget.CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(
          android.widget.CompoundButton p0,
          boolean p1
        ) {
          target.onAddTenChecked(p1);
        }
      });
    view = finder.findRequiredView(source, 2131296266, "method 'addThousandCheched'");
    ((android.widget.CompoundButton) view).setOnCheckedChangeListener(
      new android.widget.CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(
          android.widget.CompoundButton p0,
          boolean p1
        ) {
          target.addThousandCheched(p1);
        }
      });
  }

  public static void reset(com.jitizhihui.wxassistant.MainActivity target) {
    target.editText = null;
    target.msgTextView = null;
    target.addButton = null;
    target.deleteButton = null;
    target.tipsButton = null;
    target.stopButton = null;
    target.luckButton = null;
    target.getActivateButton = null;
    target.title = null;
  }
}
