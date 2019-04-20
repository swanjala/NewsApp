package com.example.newsapp.testing;

public class SingleFragmentActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FrameLayout content = new FragmeLayou(this);
        content.setLayoutParams(new FragmeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGropu.LayoutParams.MATCH_PARENT));
        content.setId(R.id.contaier);
        setContentView(content);
    }

    public void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment, "TEST")
                .commit();
    }
    public void replaceFragment(Fragment fragment){
        getSupportFragmentManage().beginTransaction()
                .replace(R.id.container, fragment).commit();
    }
}