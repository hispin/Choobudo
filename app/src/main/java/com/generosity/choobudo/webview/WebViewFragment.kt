package com.generosity.choobudo.webview

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R
import com.generosity.choobudo.common.common.Constant.IS_SCROLL_DWN_KEY
import com.generosity.choobudo.common.common.Constant.URL_KEY

class WebViewFragment : Fragment() {

    private var isScrollDown: Boolean?=null
    private var webWebsite: WebView?=null
    private var url: String?=null
    private var webProgressBar:ProgressBar?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url=it.getString(URL_KEY)
            isScrollDown=it.getBoolean(IS_SCROLL_DWN_KEY,false)
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(url: String, isScrollDown: Boolean)=WebViewFragment().apply {
            arguments=Bundle().apply {
                putString(URL_KEY, url)
                putBoolean(IS_SCROLL_DWN_KEY, isScrollDown)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_web_view, container, false)
        webProgressBar = view.findViewById(R.id.webProgressBar)
        loadUrl(view)
        return view
    }

    private fun loadUrl(view: View) {
        webWebsite= view.findViewById(R.id.webWebsite) as WebView
        webWebsite?.webViewClient = WebViewClient()
//        webWebsite?.setInitialScale(1)
        webWebsite?.isScrollbarFadingEnabled = true
        webWebsite?.scrollBarStyle=WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webWebsite?.isHorizontalScrollBarEnabled = false
        webWebsite?.settings?.javaScriptEnabled = true
//        //webWebsite?.settings?.userAgentString = "First Webview"
        webWebsite?.settings?.loadWithOverviewMode = true
        webWebsite?.settings?.useWideViewPort = true

        webWebsite?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (URLUtil.isNetworkUrl(url)) {
                    return false
                }
                if (url?.let { getPackageInfoCompat(it) } == true) {
                    val intent=Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                } else {
                    // do something if app is not installed
                }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                webProgressBar?.visibility = View.VISIBLE
                if(isScrollDown == true) {
                    view?.scrollY=200
                }
            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                webProgressBar?.visibility = View.GONE
            }


            fun getPackageInfoCompat(packageName: String, flags: Int = 0): Boolean {
                val pm: PackageManager=context?.packageManager!!
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                     try {
                         pm.getPackageInfo(
                             packageName, PackageManager.PackageInfoFlags.of(flags.toLong())
                         )
                         return true
                     }catch (e: PackageManager.NameNotFoundException) {
                     }
                } else {
                    return false
                    //@Suppress("DEPRECATION") getPackageInfo(packageName, flags)
                }
                return false
            }

//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//
//            }
        }

        url?.let { webWebsite?.loadUrl(it) }
    }

}