package old.utils;

import org.testng.reporters.EmailableReporter2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.testng.internal.Utils;

/**
 * Reporter that generates a single-page HTML report of the test results.
 * <p>
 * Based on an earlier implementation by Paul Mendelson.
 * </p>
 * 
 * @author Abraham Lin
 */
public class CustomEmailableReporter extends EmailableReporter2 {

	@Override
	protected PrintWriter createWriter(String outdir) throws IOException {
		new File(outdir).mkdirs();
		return new PrintWriter(new BufferedWriter(new FileWriter(new File(outdir, "emailable-report2.html"))));
	}

	@Override
	protected void writeStylesheet() {
		writer.print("<style type=\"text/css\">");
		writer.print("table {margin-bottom:10px;border-collapse:collapse;empty-cells:show}");
		writer.print("th,td {border:1px solid #009;padding:.25em .5em}");
		writer.print("th {vertical-align:bottom}");
		writer.print("td {vertical-align:top}");
		writer.print("table a {font-weight:bold}");
		writer.print(".stripe td {background-color: #E6EBF9}");
		writer.print(".num {text-align:right}");
		writer.print(".passedodd td {background-color: #CCFF99}");
		writer.print(".passedeven td {background-color: #99FF99}");
		writer.print(".skippedodd td {background-color: #DDD}");
		writer.print(".skippedeven td {background-color: #CCC}");
		writer.print(".failedodd td,.attn {background-color: #FFCCCC}");
		writer.print(".failedeven td,.stripe .attn {background-color: #FF9999}");
		writer.print(".stacktrace {white-space:pre;font-family:monospace}");
		writer.print(".totop {font-size:85%;text-align:center;border-bottom:2px solid #000}");
		writer.print("</style>");
	}

	@Override
	protected void writeReporterMessages(List<String> reporterMessages) {
		writer.print("<div class=\"messages\">");
		Iterator<String> iterator = reporterMessages.iterator();
		assert iterator.hasNext();
		// writer.print(Utils.escapeHtml(iterator.next()));
		writer.print(iterator.next());
		while (iterator.hasNext()) {
			writer.print("<br/>");
			// writer.print(Utils.escapeHtml(iterator.next()));
			writer.print(iterator.next());
		}
		writer.print("</div>");
	}

	@Override
	protected void writeStackTrace(Throwable throwable) {
		writer.print("<div class=\"stacktrace\">");
		// writer.print(Utils.stackTrace(throwable, true)[0]);
		String message = throwable.getMessage();
		if (message != null && !message.startsWith(" === Validation Results ===")) {
			writer.print(Utils.escapeHtml(throwable.getMessage()));
		}
		writer.print("</div>");
	}
}
