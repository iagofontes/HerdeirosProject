package br.com.fontes.projetinhobossini;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;
    public VideoView vv;

    public VideoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    /*public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View newView = inflater.inflate(R.layout.fragment_video, container, false);
        vv = (VideoView) newView.findViewById(R.id.vView);
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //mandar para a próxima página da aplicação.
                //Não sei se irá funcionar
                /*try{
//                    VideoFragment.this.finalize();
                    VideoFragment.this.finalize();
                    EmailFragment ef = new EmailFragment();
//                    ef.
                }catch(Exception e){
                    Toast.makeText(getContext(), "Erros ao finalizar vídeo.",Toast.LENGTH_SHORT).show();
                } catch (Throwable throwable) {
                    Toast.makeText(getContext(), "Erros ao finalizar vídeo, problemas com trowable.",
                            Toast.LENGTH_SHORT).show();
                    throwable.printStackTrace();
                }*/
            }
        });
        vv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                vv.canPause();
                vv.pause();
            }
        });
        return newView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if(vv != null){

            Uri src = Uri.parse("http://villopim.com.br/android/video.3gp");
            vv.setVideoURI(src);
            vv.setMediaController(new MediaController(getContext()));
            vv.start();
        }
    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
