package com.example.nurzhaussyn.kbtuapplication.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.nurzhaussyn.kbtuapplication.CourseActivity;
import com.example.nurzhaussyn.kbtuapplication.R;
import com.example.nurzhaussyn.kbtuapplication.ViewPagerAdapter;

import java.util.HashMap;
import java.util.Map;

public class CoursesFragment extends Fragment implements View.OnClickListener{

    private Button btn1_1,btn1_2,btn1_3,btn1_4,btn1_5,btn2_1,btn2_2;
    private Button btn2_3,btn2_4,btn2_5,btn3_1,btn3_2,btn3_3,btn3_4,btn3_5;
    ViewPager viewPager;
    LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;
    Map<String,String[]> data;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        init();
        View v = inflater.inflate(R.layout.courses, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        sliderDotsPanel = (LinearLayout) v.findViewById(R.id.sliderDots);
        initButtons(v);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];
        for(int i = 0; i < dotsCount; i++){
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(9, 0, 9, 0);
            sliderDotsPanel.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return v;
    }

    private void initButtons(View v) {
        btn1_1 = (Button) v.findViewById(R.id.btn1_1);
        btn1_1.setOnClickListener(this);
        btn1_2 = (Button) v.findViewById(R.id.btn1_2);
        btn1_2.setOnClickListener(this);
        btn1_3 = (Button) v.findViewById(R.id.btn1_3);
        btn1_3.setOnClickListener(this);
        btn1_4 = (Button) v.findViewById(R.id.btn1_4);
        btn1_4.setOnClickListener(this);
        btn1_5 = (Button) v.findViewById(R.id.btn1_5);
        btn1_5.setOnClickListener(this);
        btn2_1 = (Button) v.findViewById(R.id.btn2_1);
        btn2_1.setOnClickListener(this);
        btn2_2 = (Button) v.findViewById(R.id.btn2_2);
        btn2_2.setOnClickListener(this);
        btn2_3 = (Button) v.findViewById(R.id.btn2_3);
        btn2_3.setOnClickListener(this);
        btn2_4 = (Button) v.findViewById(R.id.btn2_4);
        btn2_4.setOnClickListener(this);
        btn2_5 = (Button) v.findViewById(R.id.btn2_5);
        btn2_5.setOnClickListener(this);
        // btn2_6 = (Button) v.findViewById(R.id.btn2_6);
        //btn2_6.setOnClickListener(this);
        btn3_1 = (Button) v.findViewById(R.id.btn3_1);
        btn3_1.setOnClickListener(this);
        btn3_2 = (Button) v.findViewById(R.id.btn3_2);
        btn3_2.setOnClickListener(this);
        btn3_3 = (Button) v.findViewById(R.id.btn3_3);
        btn3_3.setOnClickListener(this);
        btn3_4 = (Button) v.findViewById(R.id.btn3_4);
        btn3_4.setOnClickListener(this);
        btn3_5 = (Button) v.findViewById(R.id.btn3_5);
        btn3_5.setOnClickListener(this);

    }

    void init(){
        //Aliden JUST DO IT
        data = new HashMap<String, String[]>();
        data.put("Parallel Programming", new String[]{"Parallel Programming","Assel Zholdasovna Akzhalova\nAskhatuly Aidos\nSeitbekova Yerkezhan","2-year 1-semester","Advanced computer architectures, theories of parallel computing, system resources optimization, efficient programming languages and application requirements of cost-effective computer systems. Classical results and practical insights into implementing parallel algorithms on actual parallel machines. Parallel programming using C++ and either MPI or PVM, executing on either a network of PCs or a cluster computer. The unit contains lectures and lab works."});
        data.put("Software Engineering", new String[]{"Software Engineering","Assel Zholdasovna Akzhalova\nАсхатулы Айдос\nDamir Yeliussizov\nЖумабаев Акылбек\nSeitbekova Yerkezhan","3-year 1-semester","The course objectives are: to know: software life-cycle models and phases, software development standards, basic concepts of BPMN and UML; to be able: to analyze systems and specify system requirements, to create UML diagrams and technical documentation; to organize implementation, testing and deployment of software; to have an idea: software quality management standards, software costs management, Model Driven Development, Project patterns."});
        data.put("Algorithms and Data Str.", new String[]{"Algorithms and Data Structure","Askar Akshabayev, Beisenbek Baisakov, Damir Yeliussizov, Полищук Екатерина Владимировна","2-year 1-semester","This course is designed to teach efficient use of data structures and algorithms to solve problems. Students study the logical relationship between data structures associated with a problem and the physical representation. Topics include introduction to algorithms and data organization, arrays, stacks, queues, single and double linked lists, trees, graphs, internal sorting, hashing, and heap structures. Hands-on exercises are required."});
        data.put("Programming Technologies", new String[]{"Programming Technologies","Askar Akshabayev, Beisenbek Baisakov, Bobur Mukhsimbayev, Бузаубаков Раман","1-year 2-semester","The .NET Framework is an integral Microsoft Windows component designed to support next-generation applications and services. Many fundamentals of the .NET Framework will be familiar to developers who have worked in other object-oriented development environments; however, the .NET Framework also includes many unique elements that will be new to even the most experienced developers. This course provides an overview of .NET Framework programming."});
        data.put("Foundation of WEB Programm.", new String[]{"Foundation of WEB Programming","Baisakov Beisenbek, Mihalko Shukhrat","2-year 1-semester","About This course contains brand-new topics on XHTML, wireless Internet development, web security, accessibility, Python, and PHP. Learn all the basics of programming in the context of Internet/web markup languages(HTML, Dynamic HTML and XML) and scripting languages (JavaScript, VBScript, Perl, Python and PHP) Through this course that covers virtually every web development skill now in demand, from building dynamic pages through server and client-side scripting; XML"});
        data.put("Ecology and Sustain. Developm.", new String[]{"Ecology and Sustainable Development","Байзыханов Ернар, Дастан Марат, Байгозин Азамат","3-year 1-semester","Environmental issues, environmental protection, to demonstrate the knowledge and all the variety, the relationships between plants, animals and their habitat. The main objective of the course is greening consciousness of students of all disciplines and fostering a sense of responsibility for the natural environment. Knowledge of the basic laws of interaction between the components of the biosphere and the interference effects of human activities, especially in the context of intensification of nature, it is necessary to solve practical problems in the plane of the relations between society and the biosphere as a whole."});
        data.put("Rock and Fluid Properties", new String[]{"Rock and Fluid Properties","Байзыханов Ернар, Дастан Марат, Байгозин Азамат","3-year 2-semester","This subject is about petroleum producing. It is not found in underground rivers or caverns, but in pore spaces between the grains of porous sedimentary rocks. A piece of porous sedimentary rock. The pore spaces are the white areas between the dark grains. It is within such pore spaces that fluids such as oil, natural gas, or water can be found in the subsurface."});
        data.put("Fundamentals of Robotics", new String[]{"Fundamentals of Robotics","Мергембаева Гуляйм","2-year 1-semester","How do you create robots that operate well in the real world? Learn the key math concepts and tools used to design robots that excel in navigating our complex, unstructured world in environments such as aerospace, automotive, manufacturing and healthcare.\n" +
                "In this course, part of the Robotics MicroMasters program, you will learn how to apply concepts from linear algebra, geometry and group theory and the tools to configure and control the motion of manipulators and mobile robots.\n"});
        data.put("Basics of Reservoir Engineering", new String[]{"Basics of Reservoir Engineering","Urmat M. Tynaliev, Beibit Akbayev","3-year 3-semester","This course is designed to structure students learning of basic principles of reservoir engineering and get hands on experience in applying that knowledge in solving various problems sets. The class is designed to increase students’ self-study skills. Hence, students are expected to deliberately allocate enough time and energy to read, understand and apply knowledge and skills in the class. Lectures are going to be conducted in the form of discussion, based on what students learned and missed while working on a Problem set. "});
        data.put("Общие Правила Гидравлики", new String[]{"Общие Правила Гидравлики","Аубакиров Руслан, Санатов Азамат","2-year 2-semester","Теоретически, современные системы отопления, вентиляции и кондиционирования воздуха способны удовлетворять наиболее взыскательным требованиям по климатическим условиям в помещении и по экономичности. Однако практически даже наиболее сложные системы не всегда работают так, как было обещано. В результате, с фактически созданными климатическими условиями приходится мириться, а эксплуатационные расходы оказываются выше, чем ожидалось. Часто это случается потому, что технический проект установки такой системы не удовлетворяет некоторым необходимым для стабильного и корректного управления системой условиям. "});
        data.put("Разрушение горных пород при бурении", new String[]{"Разрушение горных пород при бурении","Муканов Тлес, Базарбаева Балнур","4-year 1-semester","Процесс разрушения горных пород при бурении является слож¬ным диффузным процессом, в котором задействовано большое число факторов технического характера. Подавляющее число факторов име¬ет стохастическую природу,— это относится как к показателям меха-нических и технических свойств горных пород, так и к режимным параметрам. Многие факторы коррелированы между собой и с раз¬личной степенью воздействуют на выходные критерии: механическую скорость бурения, скорость износа рабочих органов, удаление про-дуктов разрушения пород и др."});
        data.put("Introduction to Marketing", new String[]{"Introduction to Marketing","Madina Subalova","1-year 2-semester","Marketing is one of the fundamental disciplines for the professional workers of the market: executives, managers, production organizers, members of administrative staff at various levels. Marketing is a creative activity in the administration sphere, which promotes the expansion of production by identifying customer’s inquiries and the organization of research activities aimed at satisfaction of these needs. . Study of the “Marketing” course gives to the students a meaningful help in realization of theoretical knowledge in marketing sector practically, exactly in private companies, state institutions."});
        data.put("Corporate Finance Data", new String[]{"Corporate Finance Data","D. Hoskins","2-year 1-semester","Corporate Finance Data is a required course for all undergraduate finance majors and a possible elective for non-finance majors who have had some exposure to the subject of finance.  We will begin with how corporations make capital investment decisions, the tools used in analysis and how risk is assessed.  The course will then examine how corporations raise capital in the market using various financial instruments.  We will conclude with how corporations determine the optimal capital structure.  Throughout the course we will consider how corporations strive to maximize the value of their shareholders’ wealth while consistently acting in an ethical manner with all stakeholders"});
        data.put("Data Analysis Econ. Forecas.", new String[]{"Data Analysis and Economic Forecasting","Urmat M. Tynaliev","2-year 2-semester","The main aim of this course is to provide students with both quantitative and qualitative data analysis tools and methods that can be applied to study various managerial/organizational problems as well as in forecasting economic and business time series such as sales, expenditures, and macroeconomic variables such as GDP, interest rates, inflation, stock market, etc."});
        data.put("Principles of Economics", new String[]{"Principles of Economics","Assel K. Jumasseitova","1-year 2-semester","This course is an introduction to economic concepts and basic economic theory. The course is split between the study of microeconomics, which focuses on the decision making of individual consumers and firms and macroeconomics with focuses on aggregate level economic questions such as interest rates, government spending, among others. In this course we will develop economic tools to analyze and evaluate public policies, poverty and welfare questions, and other applied topics."});
        data.put("Intro. to Microeconom.", new String[]{"Introduction to Microeconomics","Assel Jumasseitova","3-year 1-semester","The purpose of the course is to develop basic analytical tools of microeconomics and apply these tools to study contemporary issues and policies in economics. A good grasp of microeconomics is critical in many instances of business managerial decision making, as well as in designing and understanding of public policy, and more generally in appreciating of a modern economy functions.Generally speaking, different types of analysis is offered to understand major segments of the economy—specifically, consumers and producers—as they interact in output markets and resource markets, and to understand the government’s impact on these specific economic units."});
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), CourseActivity.class);
        Button btn = (Button) view;
        intent.putExtra("arrData", data.get(btn.getText().toString().trim()));
        startActivity(intent);
    }
}
