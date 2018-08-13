import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {


	public String[] edit(String[] e) {
		boolean changed = true;

		ArrayList<String[]> entry = new ArrayList<String[]>();
		for (String s : e) {
			entry.add(newEntry(s));
		}

		while (changed) {
			changed = false;

			for (int i = entry.size() - 1; i >= 1; i--) {
				String[] last = entry.get(i);
				for (int j = i - 1; j >= 0; j--) {
					String[] prev = entry.get(j);
					if (overlap(last, prev)) {
						entry.remove(i);
						entry.set(j, merge(last, prev));
						changed = true;
						break;
					}
				}
			}
		}

		ArrayList<String> res = new ArrayList<String>();
		for (String[] ss : entry) {
			res.add(print(ss));
		}
		Collections.sort(res);

		return res.toArray(new String[0]);

	}

	private String print(String[] ss) {
		StringBuilder sb = new StringBuilder();
		for (String s :ss ) {
			sb.append(s);
			sb.append(" ");
		}
		return sb.toString().trim();
	}

	private String[] merge(String[] e1, String[] e2) {
		ArrayList<String> res = new ArrayList<String>();
		for (String s : e1) {
			if (!res.contains(s)) {
				res.add(s);
			}
		}
		for (String s : e2) {
			if (!res.contains(s)) {
				res.add(s);
			}
		}

		Collections.sort(res);
		return res.toArray(new String[0]);
	}

	private boolean overlap(String[] e1, String[] e2) {
		String[] merged = merge(e1, e2);
		return e1.length + e2.length - merged.length >= 2;
	}

	private String[] newEntry(String s) {
		ArrayList<String> res = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(s);
		while (st.hasMoreTokens()) {
			res.add(st.nextToken());
		}
		Collections.sort(res);
		return res.toArray(new String[0]);
	}
}
