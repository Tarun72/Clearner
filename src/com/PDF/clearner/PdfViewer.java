package com.PDF.clearner;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import android.content.Intent;

import com.Evil.clearner.Introduction;
import com.Evil.clearner.R;

public class PdfViewer extends PdfViewerActivity {

	@Override
	public int getNextPageImageResource() {
		return R.drawable.right_arrow;
	}

	@Override
	public int getPdfPageNumberEditField() {
		return R.id.pagenum_edit;
	}

	@Override
	public int getPdfPageNumberResource() {
		return R.layout.dialog_pagenumber;
	}

	@Override
	public int getPdfPasswordEditField() {
		return R.id.etPassword;
	}

	@Override
	public int getPdfPasswordExitButton() {
		return R.id.btExit;
	}

	@Override
	public int getPdfPasswordLayoutResource() {
		return R.layout.pdf_file_password;
	}

	@Override
	public int getPdfPasswordOkButton() {
		return R.id.btOK;
	}

	@Override
	public int getPreviousPageImageResource() {
		return R.drawable.left_arrow;
	}

	@Override
	public int getZoomInImageResource() {
		return R.drawable.zoom_in;

	}

	@Override
	public int getZoomOutImageResource() {
		return R.drawable.zoom_out;
	}

	@Override
	public void onBackPressed() {
		if (Introduction.showPDF) {
			Introduction.showPDF = false;
			Intent intent = new Intent(this, Introduction.class);
			startActivity(intent);
		}
		
		finish();
	}
}
