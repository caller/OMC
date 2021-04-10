/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

namespace LizardTech.LidarSDK {

using System;
using System.Runtime.InteropServices;

public class PointWriter : Object {
  private HandleRef swigCPtr;

  public PointWriter(IntPtr cPtr, bool cMemoryOwn) : base(LidarDSDKPINVOKE.PointWriter_SWIGUpcast(cPtr), cMemoryOwn) {
    swigCPtr = new HandleRef(this, cPtr);
  }

  public static HandleRef getCPtr(PointWriter obj) {
    return (obj == null) ? new HandleRef(null, IntPtr.Zero) : obj.swigCPtr;
  }

  ~PointWriter() {
    Dispose();
  }

  public override void Dispose() {
    lock(this) {
      if (swigCPtr.Handle != IntPtr.Zero) {
        if (swigCMemOwn) {
          swigCMemOwn = false;
          LidarDSDKPINVOKE.delete_PointWriter(swigCPtr);
        }
        swigCPtr = new HandleRef(null, IntPtr.Zero);
      }
      GC.SuppressFinalize(this);
      base.Dispose();
    }
  }

  public void setMetadata(Metadata metadata) {
    LidarDSDKPINVOKE.PointWriter_setMetadata(swigCPtr, Metadata.getCPtr(metadata));
    if (LidarDSDKPINVOKE.SWIGPendingException.Pending) throw LidarDSDKPINVOKE.SWIGPendingException.Retrieve();
  }

  public Metadata getMetadata() {
    Metadata ret = new Metadata(LidarDSDKPINVOKE.PointWriter_getMetadata(swigCPtr), false);
    if (LidarDSDKPINVOKE.SWIGPendingException.Pending) throw LidarDSDKPINVOKE.SWIGPendingException.Retrieve();
    return ret;
  }

  public virtual void setQuantization(double[] scale, double[] offset) {
    LidarDSDKPINVOKE.PointWriter_setQuantization(swigCPtr, scale, offset);
    if (LidarDSDKPINVOKE.SWIGPendingException.Pending) throw LidarDSDKPINVOKE.SWIGPendingException.Retrieve();
  }

  public virtual double[] getScale() {
   IntPtr raw = LidarDSDKPINVOKE.PointWriter_getScale(swigCPtr);
    if (LidarDSDKPINVOKE.SWIGPendingException.Pending) throw LidarDSDKPINVOKE.SWIGPendingException.Retrieve();
   double[] ret = null;
   if(raw != IntPtr.Zero)
   {
      ret = new double[3];
      System.Runtime.InteropServices.Marshal.Copy(raw, ret, 0, 3);
   }
   return ret;
}

  public virtual double[] getOffset() {
   IntPtr raw = LidarDSDKPINVOKE.PointWriter_getOffset(swigCPtr);
    if (LidarDSDKPINVOKE.SWIGPendingException.Pending) throw LidarDSDKPINVOKE.SWIGPendingException.Retrieve();
   double[] ret = null;
   if(raw != IntPtr.Zero)
   {
      ret = new double[3];
      System.Runtime.InteropServices.Marshal.Copy(raw, ret, 0, 3);
   }
   return ret;
}

  public virtual long write(Bounds bounds, double fraction, PointInfo pointInfo, ProgressDelegate arg3) {
    long ret = LidarDSDKPINVOKE.PointWriter_write(swigCPtr, Bounds.getCPtr(bounds), fraction, PointInfo.getCPtr(pointInfo), ProgressDelegate.getCPtr(arg3));
    if (LidarDSDKPINVOKE.SWIGPendingException.Pending) throw LidarDSDKPINVOKE.SWIGPendingException.Retrieve();
    return ret;
  }

  public void setChunckSize(uint size) {
    LidarDSDKPINVOKE.PointWriter_setChunckSize(swigCPtr, size);
    if (LidarDSDKPINVOKE.SWIGPendingException.Pending) throw LidarDSDKPINVOKE.SWIGPendingException.Retrieve();
  }

  public virtual bool supportedChannels(PointInfo inputPointInfo, PointInfo supportedPointInfo) {
    bool ret = LidarDSDKPINVOKE.PointWriter_supportedChannels(swigCPtr, PointInfo.getCPtr(inputPointInfo), PointInfo.getCPtr(supportedPointInfo));
    if (LidarDSDKPINVOKE.SWIGPendingException.Pending) throw LidarDSDKPINVOKE.SWIGPendingException.Retrieve();
    return ret;
  }

}

}