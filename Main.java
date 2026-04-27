import static java.lang.System.getProperty;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.*;
public class Main {
  public static String keyAsValue(List<String> HM, String vals, String nextT, AtomicBoolean need_or_not) {
    String weneedit = "";
    for (var H: HM) {
      if (need_or_not.get()) {
        return "";
      }
      if (H.contains(nextT) && !nextT.isBlank()) {
        weneedit =H.split(" - ")[0];
        need_or_not.set(true);
        return weneedit;
      }
    }
    for (var R : HM) {
      if (need_or_not.get()) {
        need_or_not.set(false);
        return "";
      }
      if (R.contains(String.format("- %s", vals))) {
        weneedit = R.split(" - ")[0];
        break;
      }
    }
    return weneedit;
  }
  public static String nextNotError(List<String> ls, String elem){
        if (ls.indexOf(elem) == ls.size() - 1){
            return "";
        }
        return String.format("%s %s",elem,ls.get(ls.indexOf(elem)+1));
  }
  public static void main(String[] args) throws IOException {
    var locale = Locale.getDefault();
    var bundle = ResourceBundle.getBundle("crablocale",locale);
    if (args.length == 0) {
      System.out.println(bundle.getString("usage"));
      System.out.println(bundle.getString("wherex"));
      System.out.println(bundle.getString("repeat"));
      System.out.println(bundle.getString("exclude"));
      System.out.println(bundle.getString("nodict"));
      System.out.println(bundle.getString("shuffle"));
      System.out.println(bundle.getString("short"));
      System.out.println(bundle.getString("noprefix"));
      System.out.println(bundle.getString("booleanusage"));
      System.exit(1);
    }
    
    var word = args[0];
    var backup = args[0];
    var notprefix = Boolean.parseBoolean(Optional.ofNullable(getProperty("noprefix")).orElse(Boolean.toString(false)));
    var prefixes = "\\b(at|in|of|is|on|am|are|which|why|whom|where|what|when)\\b";
    if (notprefix) {
      word = word.replaceAll(prefixes,"");
      backup = backup.replaceAll(prefixes,"");
    }
    var noneedagain = new AtomicBoolean(false);
    var exclude = Boolean.parseBoolean(Optional.ofNullable(getProperty("exclude")).orElse(Boolean.toString(false)));
    var repeats = Integer.parseInt(Optional.ofNullable(getProperty("repeat")).orElse(Integer.toString(1)));
    var shuffle = Boolean.parseBoolean(Optional.ofNullable(getProperty("shuffle")).orElse(Boolean.toString(false)));
    var short_ = Boolean.parseBoolean(Optional.ofNullable(getProperty("short")).orElse(Boolean.toString(false)));
    var nodict = Boolean.parseBoolean(Optional.ofNullable(getProperty("nodict")).orElse(Boolean.toString(false)));
    var values = new ArrayList<String>();
    var favorites = new ArrayList<String>();
    var keysnow = new ArrayList<String>();
    if (!nodict) {
      BufferedReader dictHandle = null;
      try {
        dictHandle = Files.newBufferedReader(Paths.get("./dict.txt"));
      } catch(IOException nodicterror) {
        System.out.println(bundle.getString("errornodict"));
        System.out.println(bundle.getString("hintnodict"));
        System.exit(1);
      }
      String initial = dictHandle.readLine();
      values.add(initial);
      keysnow.add(initial.split(" - ")[0]);
      favorites.add(initial.split(" - ")[1]);
      while (initial != null) {
        values.add(initial);
        keysnow.add(initial.split(" - ")[0]);
        favorites.add(initial.split(" - ")[1]);
        initial = dictHandle.readLine();
      }
    }
    var never = new ArrayList<String>();
    var tokens = word.split(" ");
    var some_details = Arrays.asList(tokens);
    var keys = new ArrayList<String>();
    var some = new Random();
    String TS = "";
    for (var K = 0; K < repeats; K++) {
      keys.clear();
      word = backup;
      for (var R : values) {
        TS = R.split(" - ")[1];
        if (!(word.contains(TS)))
          continue;
        if (noneedagain.get()) {
          noneedagain.set(false);
          break;
        }
        word = word.replaceAll("\\b"+TS+"\\b",keyAsValue(values,TS,nextNotError(Arrays.asList(tokens),(TS)),noneedagain));
      }
      var TKS = word.split(" ");
      for (int RE = 0;RE < TKS.length; RE++) {
        if (exclude) {
          int chanceToSkip = some.nextInt(1,100);
          if (chanceToSkip >= 70) {
            TKS[RE] = "";
            continue;
          }
        }
        if (keysnow.contains(TKS[RE])) {
          word = word.replaceAll("\\b"+TS+"\\b",keyAsValue(values,TS,nextNotError(Arrays.asList(tokens),TS),noneedagain));
          continue;
        } else {
        if (short_) {
          if (TKS[RE].length() >= 4) {
            TKS[RE] = TKS[RE].substring(0,some.nextInt(1,4));
          }
        } else {
          TKS[RE] = TKS[RE].substring(0,some.nextInt(1,TKS[RE].length()));
        }
        }
      }
      if (shuffle) {
        Collections.shuffle(Arrays.asList(TKS), some);
      }
      word = String.join("",TKS);
      System.out.println(word);
    }
  }
}