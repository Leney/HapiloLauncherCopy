/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\AidlServer\\src\\com\\zk\\server\\IZkScmService.aidl
 */
package com.zk.server;
public interface IZkScmService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements IZkScmService
{
private static final String DESCRIPTOR = "com.zk.server.IZkScmService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zk.server.IZkScmService interface,
 * generating a proxy if needed.
 */
public static IZkScmService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof IZkScmService))) {
return ((IZkScmService)iin);
}
return new Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_openSerialPort:
{
data.enforceInterface(DESCRIPTOR);
this.openSerialPort();
reply.writeNoException();
return true;
}
case TRANSACTION_closeSerialPort:
{
data.enforceInterface(DESCRIPTOR);
this.closeSerialPort();
reply.writeNoException();
return true;
}
case TRANSACTION_DeskLamp_Mode_Breathe:
{
data.enforceInterface(DESCRIPTOR);
this.DeskLamp_Mode_Breathe();
reply.writeNoException();
return true;
}
case TRANSACTION_DeskLamp_Mode_Standard:
{
data.enforceInterface(DESCRIPTOR);
this.DeskLamp_Mode_Standard();
reply.writeNoException();
return true;
}
case TRANSACTION_DeskLamp_Mode_Shutdown:
{
data.enforceInterface(DESCRIPTOR);
this.DeskLamp_Mode_Shutdown();
reply.writeNoException();
return true;
}
case TRANSACTION_DeskLamp_Mode_Study:
{
data.enforceInterface(DESCRIPTOR);
this.DeskLamp_Mode_Study();
reply.writeNoException();
return true;
}
case TRANSACTION_DeskLamp_Mode_Invalid:
{
data.enforceInterface(DESCRIPTOR);
this.DeskLamp_Mode_Invalid();
reply.writeNoException();
return true;
}
case TRANSACTION_DeskLamp_Mode_Sleep:
{
data.enforceInterface(DESCRIPTOR);
this.DeskLamp_Mode_Sleep();
reply.writeNoException();
return true;
}
case TRANSACTION_DeskLamp_Mode_Wake:
{
data.enforceInterface(DESCRIPTOR);
this.DeskLamp_Mode_Wake();
reply.writeNoException();
return true;
}
case TRANSACTION_DeskLamp_WhiteLightBrightness:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.DeskLamp_WhiteLightBrightness(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_DeskLamp_YellowLightBrightness:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.DeskLamp_YellowLightBrightness(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_GetSensorData:
{
data.enforceInterface(DESCRIPTOR);
byte[] _result = this.GetSensorData();
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_GetDeskLampMode:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetDeskLampMode();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_GetSceneLampMode:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetSceneLampMode();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_GetScenceLightR:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetScenceLightR();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_GetScenceLightG:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetScenceLightG();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_GetScenceLightB:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetScenceLightB();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_GetScenceLightW:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetScenceLightW();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_GetScenceLightLx:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetScenceLightLx();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_GetTemperature:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetTemperature();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_GetHumidity:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetHumidity();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_GetHumanResponse:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetHumanResponse();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_GetInfrared:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetInfrared();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_SetSceneLampColor:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
this.SetSceneLampColor(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_FlushSceneLampFrame:
{
data.enforceInterface(DESCRIPTOR);
byte[] _arg0;
_arg0 = data.createByteArray();
this.FlushSceneLampFrame(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_FlushSceneLampGifWithUrl:
{
data.enforceInterface(DESCRIPTOR);
String _arg0;
_arg0 = data.readString();
this.FlushSceneLampGifWithUrl(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_FlushSceneLampGifWithResEx:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.FlushSceneLampGifWithResEx(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_PlayFaceWithAudio:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.PlayFaceWithAudio(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_PlayAudio:
{
data.enforceInterface(DESCRIPTOR);
String _arg0;
_arg0 = data.readString();
int _result = this.PlayAudio(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_PlayAudioWithNew:
{
data.enforceInterface(DESCRIPTOR);
String _arg0;
_arg0 = data.readString();
int _result = this.PlayAudioWithNew(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_PlayAudioWithResId:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _result = this.PlayAudioWithResId(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_StopAudio:
{
data.enforceInterface(DESCRIPTOR);
this.StopAudio();
reply.writeNoException();
return true;
}
case TRANSACTION_StartAudio:
{
data.enforceInterface(DESCRIPTOR);
this.StartAudio();
reply.writeNoException();
return true;
}
case TRANSACTION_PauseAudio:
{
data.enforceInterface(DESCRIPTOR);
this.PauseAudio();
reply.writeNoException();
return true;
}
case TRANSACTION_GetAudioCurrentPosition:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.GetAudioCurrentPosition();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_SetAudioPosition:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.SetAudioPosition(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_SetAudioVolume:
{
data.enforceInterface(DESCRIPTOR);
float _arg0;
_arg0 = data.readFloat();
this.SetAudioVolume(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_InitBaseUrl:
{
data.enforceInterface(DESCRIPTOR);
String _arg0;
_arg0 = data.readString();
this.InitBaseUrl(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_StartAction:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
String _arg1;
_arg1 = data.readString();
String _arg2;
_arg2 = data.readString();
String _arg3;
_arg3 = data.readString();
String _arg4;
_arg4 = data.readString();
String _arg5;
_arg5 = data.readString();
this.StartAction(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements IZkScmService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void openSerialPort() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_openSerialPort, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void closeSerialPort() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_closeSerialPort, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void DeskLamp_Mode_Breathe() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_DeskLamp_Mode_Breathe, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void DeskLamp_Mode_Standard() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_DeskLamp_Mode_Standard, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void DeskLamp_Mode_Shutdown() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_DeskLamp_Mode_Shutdown, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void DeskLamp_Mode_Study() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_DeskLamp_Mode_Study, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void DeskLamp_Mode_Invalid() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_DeskLamp_Mode_Invalid, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void DeskLamp_Mode_Sleep() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_DeskLamp_Mode_Sleep, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void DeskLamp_Mode_Wake() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_DeskLamp_Mode_Wake, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void DeskLamp_WhiteLightBrightness(int Brightness) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(Brightness);
mRemote.transact(Stub.TRANSACTION_DeskLamp_WhiteLightBrightness, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void DeskLamp_YellowLightBrightness(int Brightness) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(Brightness);
mRemote.transact(Stub.TRANSACTION_DeskLamp_YellowLightBrightness, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public byte[] GetSensorData() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetSensorData, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetDeskLampMode() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetDeskLampMode, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetSceneLampMode() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetSceneLampMode, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetScenceLightR() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetScenceLightR, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetScenceLightG() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetScenceLightG, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetScenceLightB() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetScenceLightB, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetScenceLightW() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetScenceLightW, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetScenceLightLx() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetScenceLightLx, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetTemperature() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetTemperature, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetHumidity() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetHumidity, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetHumanResponse() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetHumanResponse, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int GetInfrared() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetInfrared, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void SetSceneLampColor(int R, int G, int B) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(R);
_data.writeInt(G);
_data.writeInt(B);
mRemote.transact(Stub.TRANSACTION_SetSceneLampColor, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void FlushSceneLampFrame(byte[] frameData) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeByteArray(frameData);
mRemote.transact(Stub.TRANSACTION_FlushSceneLampFrame, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void FlushSceneLampGifWithUrl(String urlString) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(urlString);
mRemote.transact(Stub.TRANSACTION_FlushSceneLampGifWithUrl, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void FlushSceneLampGifWithResEx(int resId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(resId);
mRemote.transact(Stub.TRANSACTION_FlushSceneLampGifWithResEx, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void PlayFaceWithAudio(int index) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(index);
mRemote.transact(Stub.TRANSACTION_PlayFaceWithAudio, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int PlayAudio(String audioUrl) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(audioUrl);
mRemote.transact(Stub.TRANSACTION_PlayAudio, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int PlayAudioWithNew(String audioUrl) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(audioUrl);
mRemote.transact(Stub.TRANSACTION_PlayAudioWithNew, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int PlayAudioWithResId(int resId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(resId);
mRemote.transact(Stub.TRANSACTION_PlayAudioWithResId, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void StopAudio() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_StopAudio, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void StartAudio() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_StartAudio, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void PauseAudio() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_PauseAudio, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int GetAudioCurrentPosition() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_GetAudioCurrentPosition, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void SetAudioPosition(int pos) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(pos);
mRemote.transact(Stub.TRANSACTION_SetAudioPosition, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void SetAudioVolume(float volume) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeFloat(volume);
mRemote.transact(Stub.TRANSACTION_SetAudioVolume, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void InitBaseUrl(String baseUrl) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(baseUrl);
mRemote.transact(Stub.TRANSACTION_InitBaseUrl, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void StartAction(int actionId, String minute, String hour, String day, String month, String week) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(actionId);
_data.writeString(minute);
_data.writeString(hour);
_data.writeString(day);
_data.writeString(month);
_data.writeString(week);
mRemote.transact(Stub.TRANSACTION_StartAction, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_openSerialPort = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_closeSerialPort = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_DeskLamp_Mode_Breathe = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_DeskLamp_Mode_Standard = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_DeskLamp_Mode_Shutdown = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_DeskLamp_Mode_Study = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_DeskLamp_Mode_Invalid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_DeskLamp_Mode_Sleep = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_DeskLamp_Mode_Wake = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_DeskLamp_WhiteLightBrightness = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_DeskLamp_YellowLightBrightness = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_GetSensorData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_GetDeskLampMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_GetSceneLampMode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_GetScenceLightR = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_GetScenceLightG = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_GetScenceLightB = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_GetScenceLightW = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_GetScenceLightLx = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_GetTemperature = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_GetHumidity = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_GetHumanResponse = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_GetInfrared = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_SetSceneLampColor = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
static final int TRANSACTION_FlushSceneLampFrame = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
static final int TRANSACTION_FlushSceneLampGifWithUrl = (android.os.IBinder.FIRST_CALL_TRANSACTION + 25);
static final int TRANSACTION_FlushSceneLampGifWithResEx = (android.os.IBinder.FIRST_CALL_TRANSACTION + 26);
static final int TRANSACTION_PlayFaceWithAudio = (android.os.IBinder.FIRST_CALL_TRANSACTION + 27);
static final int TRANSACTION_PlayAudio = (android.os.IBinder.FIRST_CALL_TRANSACTION + 28);
static final int TRANSACTION_PlayAudioWithNew = (android.os.IBinder.FIRST_CALL_TRANSACTION + 29);
static final int TRANSACTION_PlayAudioWithResId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 30);
static final int TRANSACTION_StopAudio = (android.os.IBinder.FIRST_CALL_TRANSACTION + 31);
static final int TRANSACTION_StartAudio = (android.os.IBinder.FIRST_CALL_TRANSACTION + 32);
static final int TRANSACTION_PauseAudio = (android.os.IBinder.FIRST_CALL_TRANSACTION + 33);
static final int TRANSACTION_GetAudioCurrentPosition = (android.os.IBinder.FIRST_CALL_TRANSACTION + 34);
static final int TRANSACTION_SetAudioPosition = (android.os.IBinder.FIRST_CALL_TRANSACTION + 35);
static final int TRANSACTION_SetAudioVolume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 36);
static final int TRANSACTION_InitBaseUrl = (android.os.IBinder.FIRST_CALL_TRANSACTION + 37);
static final int TRANSACTION_StartAction = (android.os.IBinder.FIRST_CALL_TRANSACTION + 38);
}
public void openSerialPort() throws android.os.RemoteException;
public void closeSerialPort() throws android.os.RemoteException;
public void DeskLamp_Mode_Breathe() throws android.os.RemoteException;
public void DeskLamp_Mode_Standard() throws android.os.RemoteException;
public void DeskLamp_Mode_Shutdown() throws android.os.RemoteException;
public void DeskLamp_Mode_Study() throws android.os.RemoteException;
public void DeskLamp_Mode_Invalid() throws android.os.RemoteException;
public void DeskLamp_Mode_Sleep() throws android.os.RemoteException;
public void DeskLamp_Mode_Wake() throws android.os.RemoteException;
public void DeskLamp_WhiteLightBrightness(int Brightness) throws android.os.RemoteException;
public void DeskLamp_YellowLightBrightness(int Brightness) throws android.os.RemoteException;
public byte[] GetSensorData() throws android.os.RemoteException;
public int GetDeskLampMode() throws android.os.RemoteException;
public int GetSceneLampMode() throws android.os.RemoteException;
public int GetScenceLightR() throws android.os.RemoteException;
public int GetScenceLightG() throws android.os.RemoteException;
public int GetScenceLightB() throws android.os.RemoteException;
public int GetScenceLightW() throws android.os.RemoteException;
public int GetScenceLightLx() throws android.os.RemoteException;
public int GetTemperature() throws android.os.RemoteException;
public int GetHumidity() throws android.os.RemoteException;
public int GetHumanResponse() throws android.os.RemoteException;
public int GetInfrared() throws android.os.RemoteException;
public void SetSceneLampColor(int R, int G, int B) throws android.os.RemoteException;
public void FlushSceneLampFrame(byte[] frameData) throws android.os.RemoteException;
public void FlushSceneLampGifWithUrl(String urlString) throws android.os.RemoteException;
public void FlushSceneLampGifWithResEx(int resId) throws android.os.RemoteException;
public void PlayFaceWithAudio(int index) throws android.os.RemoteException;
public int PlayAudio(String audioUrl) throws android.os.RemoteException;
public int PlayAudioWithNew(String audioUrl) throws android.os.RemoteException;
public int PlayAudioWithResId(int resId) throws android.os.RemoteException;
public void StopAudio() throws android.os.RemoteException;
public void StartAudio() throws android.os.RemoteException;
public void PauseAudio() throws android.os.RemoteException;
public int GetAudioCurrentPosition() throws android.os.RemoteException;
public void SetAudioPosition(int pos) throws android.os.RemoteException;
public void SetAudioVolume(float volume) throws android.os.RemoteException;
public void InitBaseUrl(String baseUrl) throws android.os.RemoteException;
public void StartAction(int actionId, String minute, String hour, String day, String month, String week) throws android.os.RemoteException;
}
