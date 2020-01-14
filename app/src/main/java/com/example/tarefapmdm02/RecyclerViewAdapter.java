package com.example.tarefapmdm02;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter  extends RecyclerView.Adapter {

    ArrayList pedidos;
    int idUser;
    String tipoPedido;
    String tipoUsuario;
    Context contexto;

    public RecyclerViewAdapter(ArrayList<Pedido> pedidos,String tipoPedido,String tipoUsuario,Context contexto) {
        this.pedidos=pedidos;
        this.tipoPedido=tipoPedido;
        this.tipoUsuario=tipoUsuario;
        this.contexto=contexto;
    }
    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View vista=null ;

        if(tipoUsuario.equals("administrador") && tipoPedido.equals("tramite"))
             vista = mInflater.inflate(R.layout.recycler_view_layout_administrador,parent,false);
        else
            vista = mInflater.inflate(R.layout.recycler_view_layout_cliente,parent,false);

        RecyclerView.ViewHolder viewHolder = new ViewHolderPersonalizado(vista);
        return viewHolder;
    }
    public class ViewHolderPersonalizado extends RecyclerView.ViewHolder {
        TextView articulo,codigo,cantidad;
        ImageView  aceptacion,rechazo;
        public ViewHolderPersonalizado(@NonNull View itemView) {
            super(itemView);
            articulo = itemView.findViewById(R.id.articulo);
            codigo = itemView.findViewById(R.id.idpedido);
            cantidad=itemView.findViewById(R.id.cantidad);
            if(tipoUsuario.equals("administrador") && tipoPedido.equals("tramite")){
                aceptacion=itemView.findViewById(R.id.aceptacion);
                rechazo=itemView.findViewById(R.id.rechazo);
            }


        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolderPersonalizado viewHolderMeu = (ViewHolderPersonalizado) holder;
        Pedido item=(Pedido)pedidos.get(position);
        viewHolderMeu.articulo.setText(item.getItem());
        viewHolderMeu.cantidad.setText((item.getNum()+""));
        viewHolderMeu.codigo.setText(item.getCode()+"");

        if(tipoUsuario.equals("administrador") && tipoPedido.equals("tramite")) {
            viewHolderMeu.rechazo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder venta = new AlertDialog.Builder(contexto)
                            .setTitle("Eliminación de registros Tabla:Pedidos").setIcon(android.R.drawable.ic_delete)
                            .setMessage("¿Seguro de querer ELIMINAR?")
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Pedido tmp = (Pedido) (pedidos.get(position));
                                    int code = tmp.getCode();
                                    actualizaBd(code, "rechazado");
                                    pedidos.remove(position);
                                    notifyDataSetChanged();
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(contexto,"Operación cancelada", Toast.LENGTH_LONG).show();
                                    dialog.cancel();}
                            });
                    venta.create();
                    venta.show();
                }
            });
            viewHolderMeu.aceptacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///********************
                   final AlertDialog.Builder venta = new AlertDialog.Builder(contexto)
                            .setTitle("Aceptación de registros Tabla:Pedidos").setIcon(android.R.drawable.ic_dialog_info)
                            .setMessage("¿Seguro de querer ACEPTAR?")
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Pedido tmp = (Pedido) (pedidos.get(position));
                                    int code = tmp.getCode();
                                    actualizaBd(code, "aceptado");
                                    pedidos.remove(position);
                                    notifyDataSetChanged();
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(contexto,"Operación cancelada", Toast.LENGTH_LONG).show();
                                    dialog.cancel();}
                            });
                    venta.create();
                    venta.show();
                }
            });
        }
    }
    public void actualizaBd(int code,String estado){
        AdminSQLiteOpenHelper dbhelper=new AdminSQLiteOpenHelper(contexto,"administracion",null, 1);
        SQLiteDatabase bd = dbhelper.getWritableDatabase();
           bd.execSQL("update  pedidos set estado=\""+estado+"\" where codigo="+code);

    }
    @Override
    public int getItemCount() {
       return pedidos.size();
    }
}

