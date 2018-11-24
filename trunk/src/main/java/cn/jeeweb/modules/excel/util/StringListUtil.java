package cn.jeeweb.modules.excel.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 文字列配列に関するツールクラス。
 * 
 */
public class StringListUtil {

    /**
     * 二つ文字列配列を一つ配列にします。
     * 
     * @param list1
     *            　配列１
     * @param list2
     *            　配列２
     * @return　新しい配列
     */
    public static String[] combineList(String[] list1, String[] list2) {
        if (list1 == null || list1.length == 0) {
            return list2;
        }
        if (list2 == null || list2.length == 0) {
            return list1;
        }
        String[] listNew = new String[list1.length + list2.length];
        int index = 0;
        for (int i = 0; i < list1.length; i++) {
            listNew[index++] = list1[i];
        }
        for (int i = 0; i < list2.length; i++) {
            listNew[index++] = list2[i];
        }
        return listNew;
    }

    /**
     * 指定配列から、削除配列を削除します。
     * 
     * @param listSrc
     *            　指定配列
     * @param listDel
     *            　削除配列
     * @return　削除された後の配列
     */
    public static String[] removeList(String[] listSrc, String[] listDel) {
        if (listSrc == null || listSrc.length == 0) {
            return listSrc;
        }
        if (listDel == null || listDel.length == 0) {
            return listSrc;
        }

        List<String> list = new ArrayList<String>();
        for (String str : listSrc) {
            if (!inList(listDel, str)) {
                list.add(str);
            }
        }

        return list.toArray(new String[0]);
    }

    /**
     * 指定された文字列配列の各メンバーに対して、前付けと後付の文字列を追加して、<BR>
     * 変更後の文字列配列を戻ります。
     * 
     * @param list
     *            　指定された文字列配列
     * @param prefix
     *            　前付け文字列
     * @param sufix
     *            　後付の文字列
     * @return　変更後の文字列配列
     */
    public static String[] encloseList(String[] list, String prefix, String sufix) {
        if (list == null || list.length == 0) {
            return list;
        }
        if (prefix == null || sufix == null) {
            return list;
        }
        String[] listNew = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            listNew[i] = prefix + list[i] + sufix;
        }
        return listNew;
    }

    /**
     * 文字列配列に、NULLまたはあき文字列を探します。<BR>
     * 最初発見したインデックスを戻ります。<BR>
     * 文字列配列はNULLまたは、サイズはゼロの場合、－1を戻ります。<BR>
     * 探せない場合、－1を戻ります。
     * 
     * @param list
     *            　文字列配列
     * @return　インデックス
     */
    public static int findNull(String[] list) {
        if (list == null || list.length < 1) {
            return -1;
        }
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null || "".equals(list[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 文字列配列に、重複の文字列を探します。<BR>
     * 最初発見した重複のインデックスを戻ります。<BR>
     * 文字列配列はNULLまたは、サイズはゼロの場合、－1を戻ります。<BR>
     * 探せない場合、－1を戻ります。
     * 
     * @param list
     *            　文字列配列
     * @return　インデックス
     */
    public static int findRepeat(String[] list) {
        if (list == null || list.length <= 1) {
            return -1;
        }
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (list[i].equals(list[j])) {
                    return j;
                }
            }
        }

        return -1;
    }

    /**
     * 文字列配列に、指定比較文字列と同じの文字列を探します。<BR>
     * 比較文字列はNULLまたは空きの場合、FALSEを戻ります。<BR>
     * 文字列配列はNULLまたは、サイズはゼロの場合、FALSEを戻ります。
     * 
     * @param compare
     *            　比較文字列
     * @param list
     *            　文字列配列
     * @return　存在したら、TRUEを戻ります。
     */
    public static boolean findRepeat(String compare, String[] list) {
        if (compare == null || compare.length() == 0) {
            return false;
        }
        if (list == null || list.length == 0) {
            return false;
        }
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(compare)) {
                return true;
            }
        }
        return false;
    }

    public static boolean findRepeatIgnoreUnderCase(String compare, String[] list) {
        if (compare == null || compare.length() == 0) {
            return false;
        }
        if (list == null || list.length == 0) {
            return false;
        }
        for (int i = 0; i < list.length; i++) {
            if (isSameIgnoreUnderCase(list[i], compare)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSameIgnoreUnderCase(String name1, String name2) {
        if (name1 == null || name2 == null) {
            return false;
        }

        name1 = name1.replaceAll("_", "").toLowerCase();
        name2 = name2.replaceAll("_", "").toLowerCase();

        return name1.equals(name2);
    }

    /**
     * 文字列配列に、指定された文字列を探します。<BR>
     * 最初発見したインデックスを戻ります。<BR>
     * 探したい文字列はNULLの場合、-1を戻ります。<BR>
     * 文字列配列はNULLまたは、サイズはゼロの場合、-1を戻ります。
     * 
     * @param list
     *            　文字列配列
     * @param key
     *            　探したい文字列
     * @return　インデックス
     */
    public static int indexOf(String[] list, String key) {
        if (list == null || list.length == 0 || key == null) {
            return -1;
        }

        for (int i = 0; i < list.length; i++) {
            if (key.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 文字列配列に、指定された文字列が存在するかどうかをチェックします。
     * 
     * @param list
     *            　文字列配列
     * @param key
     *            　指定された文字列
     * @return　存在したら、TRUEを戻ります。
     */
    public static boolean inList(String[] list, String key) {
        if (list == null || list.length == 0 || key == null) {
            return false;
        }

        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 分割文字列を利用して、文字列配列のすべてのメンバーを一つ文字列に変換します。<BR>
     * 文字列配列はNULLまたは、サイズはゼロの場合、空き文字列を戻ります。<BR>
     * 分割文字列はNULLの場合、空き文字列を戻ります。
     * 
     * @param list
     *            　文字列配列
     * @param combine
     *            　分割文字列
     * @return　変換後の文字列
     */
    public static String join(String[] list, String combine) {
        if (list == null || list.length == 0 || combine == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.length; i++) {
            if (i != 0) {
                sb.append(combine);
            }
            sb.append(list[i]);
        }
        return sb.toString();
    }

    public static String join(int[] list, String combine) {
        if (list == null || list.length == 0 || combine == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.length; i++) {
            if (i != 0) {
                sb.append(combine);
            }
            sb.append(list[i]);
        }
        return sb.toString();
    }

    /**
     * 
     * 
     * @param addStr
     * @param list
     * @return
     */
    public static String[] addTo(String addStr, String[] list) {
        if (list == null || list.length == 0) {
            return null;
        }
        if (addStr == null || addStr.length() == 0) {
            return list;
        }
        String[] listNew = new String[list.length + 1];
        int index = 0;
        for (int i = 0; i < list.length; i++) {
            listNew[index++] = list[i];
        }
        listNew[list.length] = addStr;
        return listNew;
    }

    /**
     * 文字列リストから、文字列配列へ変換します。
     * 
     * @param list
     *            　文字列リスト
     * @return　文字列配列
     */
    public static String[] toArray(List<String> list) {
        if (list == null) {
            return null;
        }
        return (String[]) list.toArray(new String[0]);
    }

    public static String toSqlInStr(String[] valList) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < valList.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("'");
            sb.append(valList[i]);
            sb.append("'");
        }
        return sb.toString();
    }

}
