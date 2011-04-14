package br.com.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.app.*;

public class AppCadastro extends Activity {
    /** Called when the activity is first created. */
	Registro pri, reg, ult, aux;
	EditText edNome, edProf, edEmail;
	int numReg, pos;
	
	
	/*Tela Principal*/
	void carregaTelaPrincipal(){
		
		setContentView(R.layout.main);
		
		//Tratar botões da tela principal
		Button btCadPessoas = (Button) findViewById(R.principal.btCadastrar);
		Button btListaPessoas = (Button) findViewById(R.principal.btListar);
		
		btCadPessoas.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				carregaTelaCadastro();
				
			}
		});
		
		btListaPessoas.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				carregaListaPessoas();
				
			}
		});
		//Fim botões
		
	}
		
	
	/*Métodos para navegar nas demais telas*/
	void carregaTelaCadastro(){
		setContentView(R.layout.cadastro);
		
		//Botões da Tela de Cadastro
		Button btCadastrar = (Button) findViewById(R.cadastro.btCadastrar);
		Button btCancelar = (Button) findViewById(R.cadastro.btCancelar);
		
		btCancelar.setOnClickListener(new View.OnClickListener(){
			public void onClick(View arg0){
				carregaTelaPrincipal();
			}
			
		});
		
		btCadastrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try{
					reg = new Registro();
					
					edNome = (EditText) findViewById(R.cadastro.edNome);
					edProf = (EditText) findViewById(R.cadastro.edProfissao);
					edEmail = (EditText) findViewById(R.cadastro.edEmail);
					
					reg.nome = edNome.getText().toString();
					reg.profissao = edProf.getText().toString();
					reg.email = edEmail.getText().toString();
					
					
					//Tratar a fila:
					if(pri == null)
						pri = reg;
					
					reg.ant = ult;
					
					if(ult == null)
						ult = reg;
					else{
						ult.prox = reg;
						ult = reg;
					}
					
					numReg++;
					
					showMessage("Cadastro efetuado com sucesso", "Aviso");
					carregaTelaPrincipal();
					
										
				}catch(Exception e){
					showMessage("Erro ao cadastrar", "Erro");
					
				}
				
			}

			
		});
		

		
	}
	
	void carregaListaPessoas(){
		
		//Tratar lista vazia
		if(numReg == 0){
			showMessage("Nenhum registro cadastrado", "Aviso");
			
			carregaTelaPrincipal();
			
			return;
		}
		
		//Tela de pesquisa
		setContentView(R.layout.listacadastrados);
		
		//Navegação
//		pos = 1;
//		aux = pri;
//		
//		TextView txtNome = (TextView) findViewById(R.lista.txtNome);
//		TextView txtProfissao = (TextView) findViewById(R.lista.txtProfissao);
//		TextView txtEmail = (TextView) findViewById(R.lista.txtEmail);
//		
//		txtNome.setText(aux.nome);
//		txtProfissao.setText(aux.profissao);
//		txtEmail.setText(aux.email);
		
		
		//Botões
		Button btMenu = (Button) findViewById(R.lista.btMenu);
		Button btAvancar = (Button) findViewById(R.lista.btAvancar);
		Button btVoltar = (Button) findViewById(R.lista.btVoltar);
		
		btMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				carregaTelaPrincipal();
				
			}
		});
		
		btVoltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(pos == 1)
					return;
				
				pos--;
				aux = aux.ant;
				
				TextView txtNome = (TextView) findViewById(R.lista.txtNome);
				TextView txtProfissao = (TextView) findViewById(R.lista.txtProfissao);
				TextView txtEmail = (TextView) findViewById(R.lista.txtEmail);
				
				txtNome.setText(aux.nome);
				txtProfissao.setText(aux.profissao);
				txtEmail.setText(aux.email);
			}
		});
		
		btAvancar.setOnClickListener(new View.OnClickListener() {
			
				
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(numReg == 0){
					showMessage("Nenhum registro cadastrado", "Aviso");									
					return;
				}
				
				if(pos == numReg)
					return;
				
				pos++;
				aux = aux.prox;
				
				TextView txtNome = (TextView) findViewById(R.lista.txtNome);
				TextView txtProfissao = (TextView) findViewById(R.lista.txtProfissao);
				TextView txtEmail = (TextView) findViewById(R.lista.txtEmail);
				
				txtNome.setText(aux.nome);
				txtProfissao.setText(aux.profissao);
				txtEmail.setText(aux.email);
				
			}
		});
	}
	
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        numReg = 0;
        pri = ult = null;
        carregaTelaPrincipal();
        
    }
    
    private void showMessage(String msg, String title) {
		// TODO Auto-generated method stub
		AlertDialog.Builder dialogo = new AlertDialog.Builder(AppCadastro.this);
		dialogo.setTitle(title);
		dialogo.setMessage(msg);
		dialogo.setNegativeButton("OK", null);
		dialogo.show();
	}
}