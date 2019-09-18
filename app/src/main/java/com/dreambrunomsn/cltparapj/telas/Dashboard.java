package com.dreambrunomsn.cltparapj.telas;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dreambrunomsn.cltparapj.R;
import com.dreambrunomsn.cltparapj.banco.informacoes.InformacoesDao;
import com.dreambrunomsn.cltparapj.banco.informacoes.InformacoesDaoSqlite;
import com.dreambrunomsn.cltparapj.conectores.SectionsPagerAdapter;
import com.dreambrunomsn.cltparapj.utils.Utils;

public class Dashboard extends AppCompatActivity {

    private Button salvar;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        salvar = findViewById(R.id.btSalvarDados);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InformacoesDao informacoesDao = new InformacoesDaoSqlite(getBaseContext());
                boolean salvo = informacoesDao.atualizar();
                Utils.hideKeyboard(Dashboard.this);
                Toast.makeText(Dashboard.this, (salvo ? "Salvo com sucesso!" : "Falha ao salvar!"), Toast.LENGTH_LONG).show();
            }
        });

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager =  findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout =  findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
