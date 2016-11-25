package com.wr_education.dialog;

//public class SelectOneItemDialog extends AbstractDialog {
//  private Context context;
//  private String title;
//  private ArrayList<String> items;
//  private SelectOneItemAdapter adapter;
//  private Window window;
//  private ListView listview;
//  private Button colse;
//  private TextView title_view;
//  private OnItemClickListener onItemClickListener;
//	public SelectOneItemDialog(Context context,String title,ArrayList<String> items,OnItemClickListener onItemClickListener) {
//		super(context);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.select_item_one_dialog);
//		this.context=context;
//		this.title=title;
//		this.items=items;
//		this.onItemClickListener=onItemClickListener;
//		initview();
//
//	}
//	private void initview() {
//		// TODO Auto-generated method stub
//		try{
//		title_view=(TextView) findViewById(R.id.select_item_one_dialog_title);
//		listview=(ListView) findViewById(R.id.select_item_one_dialog__listview);
//		colse=(Button) findViewById(R.id.select_item_one_dialog_cancel);
//		colse.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				dismiss();
//			}
//		});
//		title_view.setText(title);
//		adapter=new SelectOneItemAdapter(context, items);
//		listview.setAdapter(adapter);
//		listview.setOnItemClickListener(onItemClickListener);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//}
