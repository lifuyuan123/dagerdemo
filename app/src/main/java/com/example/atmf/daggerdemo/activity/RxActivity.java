package com.example.atmf.daggerdemo.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.example.atmf.daggerdemo.R;
import com.example.atmf.daggerdemo.adapter.FoodAdapter;
import com.example.atmf.daggerdemo.entry.Course;
import com.example.atmf.daggerdemo.entry.FoodsBean;
import com.example.atmf.daggerdemo.entry.Student;
import com.paginate.Paginate;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {

    private List<FoodsBean> list=new ArrayList<>();
    private FoodAdapter adapter;
    private StaggeredGridLayoutManager manager;
    private RecyclerView rv;
    private SwipeRefreshLayout refreshLayout;
    private int page=1;
    private Paginate paginate;
    private boolean isLoading=false;
    private Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        rv=findViewById(R.id.rv);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("美食天下");
        refreshLayout=findViewById(R.id.swipe);
        manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        adapter=new FoodAdapter(list,this);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        adapter.setOnCallBack(new FoodAdapter.onCallBack() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent=new Intent(RxActivity.this,WebActivity.class);
                intent.putExtra("url",list.get(position).getHtml());
                intent.putExtra("name",list.get(position).getName());
                startActivity(intent);
            }
        });

        refreshLayout.setRefreshing(true);
        getData();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getData();
            }
        });
        final StringBuffer buffer=new StringBuffer();

        final Student[] students=new Student[5];
        for (int i = 0; i <5 ; i++) {
             Course[] courses=new Course[4];
            for (int j = 0; j < 4; j++) {
                Course course=new Course("科目"+j);
                courses[j]=course;
            }
            Student student=new Student("姓名"+i,"年纪"+i,"性别"+i,courses,(int)(Math.random()*30+i));
            students[i]=student;
        }


        Observer observer=new Observer<Course>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("收到消息","Disposable");
            }

            @Override
            public void onNext(Course value) {
                Log.e("收到消息",value.toString());
                buffer.append(value.getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("收到消息",e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("收到消息","onComplete");
            }
        };
        Observable.fromArray(students)
//                .map(new Function<Student, Course>() {   //可以中途处理数据
//                    @Override
//                    public Course apply(Student student) throws Exception {
//                        Course course=student.getClazz()[two];
//                        course.setName("牛逼");
//                        return course;
//                    }
//                })
                .flatMap(new Function<Student, ObservableSource<Course>>() {//可以理解为将observable传入数据分流处理（for循环相似）  最后合并返回一个observable
                    @Override
                    public ObservableSource<Course> apply(Student student) throws Exception {
                        return Observable.fromArray(student.getClazz());
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                    Observable.range(1,1)
                            .subscribe(new Observer<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Integer value) {
                                Log.e("收到消息",value+"   "+buffer.toString());
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                    }
                })
//                .filter(new Predicate<Student>() {//过滤作用
//                    @Override
//                    public boolean test(Student student) throws Exception {
//                        return student.getHight()>10;//过滤条件
//                    }
//                })
                .subscribe(observer);





               Paginate.Callbacks callbacks=new Paginate.Callbacks() {
                   @Override
                   public void onLoadMore() {//加载下一页数据
                    getData();
                   }
                   @Override
                   public boolean isLoading() {//是否正在加载
                       return isLoading;
                   }
                   @Override
                   public boolean hasLoadedAllItems() {
                       return false;
                   }
               };

               paginate=Paginate.with(rv,callbacks)
                       .setLoadingTriggerThreshold(0)
                       .build();
               paginate.setHasMoreDataToLoad(false);//设置有更多数据加载



    }

    private void getData(){
        isLoading=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        //获取Document对象
                        Document document= Jsoup.connect("http://home.meishichina.com/show-top-type-recipe-page-"+page+".html").get();

                        final Elements elements=document.select("div.pic");

                        Elements materials=document.select("p.subcontent");
                        final List<FoodsBean> foodsBeans=new ArrayList<>();
                        for (int j = 0; j < elements.size(); j++) {
                            FoodsBean foodsBean=new FoodsBean(elements.get(j).select("a").attr("title"),
                                    (elements.get(j).select("a").select("img").attr("data-src")).substring(0,(elements.get(j).select("a").select("img").attr("data-src")).indexOf("?")),
                                    elements.get(j).select("a").attr("href"),
                                    materials.get(j).text());
                            foodsBeans.add(foodsBean);
                            Log.e("获取数据",foodsBean.toString());
                        }
//                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final int size=list.size();
                            if (page==1){
                                list.clear();
                                list.addAll(foodsBeans);
                                adapter.notifyDataSetChanged();
                            }else {
                                list.addAll(foodsBeans);
                                adapter.notifyItemRangeInserted(size,foodsBeans.size());
                            }
                            isLoading=false;
                            refreshLayout.setRefreshing(false);
                            page++;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("获取数据","失败"+e.toString());
                }


            }
        }).start();

    }
}
